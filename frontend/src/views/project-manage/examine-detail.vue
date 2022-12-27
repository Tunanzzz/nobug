<template>
    <div class="outer">
        <div class="topBar">
            <div class="infoBarSkeleton" v-if="projectSpinning">
                <div class="tit">
                    <n-skeleton height="60px" width="60px" :sharp="false" />
                    <div>
                        <n-skeleton height="35px" width="200px" :sharp="false" style="margin-left:15px;" />
                        <div style="display:flex;margin-top:5px;">
                            <n-skeleton round height="20px" width="40px" :sharp="false" style="margin-left:15px;" />
                            <n-skeleton height="20px" width="40px" :sharp="false" style="margin-left:8px;" />
                        </div>
                    </div>
                </div>
                <div class="info">
                    <n-skeleton height="60px" width="200px" :sharp="false" />
                    <n-skeleton height="60px" width="200px" :sharp="false" style="margin-left:15px;"/>
                    <n-skeleton height="60px" width="200px" :sharp="false" style="margin-left:15px;"/>
                </div>
                
            </div>
            <div class="infoBar" v-if="!projectSpinning">
                <div class="tit" style="display:flex; vertical-align: center;">
                    <n-avatar
                        :size="60"
                        src="https://loghome.coding.net/static/project_icon/scenery-version-2-15.svg"
                        />
                    <div style="margin-left:15px;transform:translateY(0px)">
                        <p class="projectName">{{project.projectBaseInfo.projectName}}</p>
                        <p class="projectType">
                            <n-tag round :bordered="false" :type="importantDegreeInt2String[project.projectBaseInfo.importantDegree].tagType"
                            size="small" style="transform:translateY(2px); margin-right:8px;">
                                {{importantDegreeInt2String[project.projectBaseInfo.importantDegree].name}}
                                <template #icon>
                                    <img style="height:12px" src="../../static/紧急程度/低.svg" alt="" v-show="project.projectBaseInfo.importantDegree == 0">
                                    <img style="height:12px" src="../../static/紧急程度/中.svg" alt="" v-show="project.projectBaseInfo.importantDegree == 1">
                                    <img style="height:12px" src="../../static/紧急程度/高.svg" alt="" v-show="project.projectBaseInfo.importantDegree == 2">
                                    <img style="height:12px" src="../../static/紧急程度/紧急.svg" alt="" v-show="project.projectBaseInfo.importantDegree == 3">
                                </template>
                            </n-tag>
                            <n-tag :bordered="false" size="small">
                                {{project.projectBaseInfo.projectClassName}}
                            </n-tag>
                        </p>
                    </div>
                </div>
                <div class="info">
                    <n-statistic label="工作量" style="margin-right:40px;">
                        <template #prefix>
                        <n-icon>
                            <FileTrayFullSharp />
                        </n-icon>
                        </template>
                        <n-number-animation ref="numberAnimationInstRef" :from="0" :to="project.projectBaseInfo.timeAll" />
                        <template #suffix>
                        / {{project.projectBaseInfo.timeInDue}} <span style="font-size:14px;">人时</span>
                        <n-tag :bordered="false" size="small" type="error" style="margin-left:10px;"
                        v-show="project.projectBaseInfo.timeAll > project.projectBaseInfo.timeInDue">
                            超量 {{((project.projectBaseInfo.timeAll - project.projectBaseInfo.timeInDue) / project.projectBaseInfo.timeInDue * 100).toFixed(0)}}%
                        </n-tag>
                        <n-tag :bordered="false" size="small" type="success" style="margin-left:10px;"
                        v-show="project.projectBaseInfo.timeAll < project.projectBaseInfo.timeInDue">
                            节省 {{((project.projectBaseInfo.timeInDue - project.projectBaseInfo.timeAll) / project.projectBaseInfo.timeInDue * 100).toFixed(0)}}%
                        </n-tag>
                        </template>
                    </n-statistic>
                    <n-statistic label="持续时间" style="margin-right:40px;">
                        <template #prefix>
                        <n-icon>
                            <AlarmSharp />
                        </n-icon>
                        </template>
                        <n-number-animation ref="numberAnimationInstRef" :from="0" :to="project.projectBaseInfo.actualTime"/>
                        <template #suffix>
                        / {{project.projectBaseInfo.scheduledTime}} <span style="font-size:14px;">天</span>
                        <n-tag :bordered="false" size="small" type="error" style="margin-left:10px;"
                        v-show="project.projectBaseInfo.actualTime > project.projectBaseInfo.scheduledTime">
                            超量 {{((project.projectBaseInfo.actualTime - project.projectBaseInfo.scheduledTime) / project.projectBaseInfo.scheduledTime * 100).toFixed(0)}}%
                        </n-tag>
                        <n-tag :bordered="false" size="small" type="success" style="margin-left:10px;"
                        v-show="project.projectBaseInfo.actualTime < project.projectBaseInfo.scheduledTime">
                            节省 {{((project.projectBaseInfo.scheduledTime - project.projectBaseInfo.actualTime) / project.projectBaseInfo.scheduledTime * 100).toFixed(0)}}%
                        </n-tag>
                        </template>
                    </n-statistic>
                    <n-statistic label="状态" style="margin-right:5px;">
                        <template #prefix>
                            <n-icon>
                                <HelpOutline v-show="project.projectBaseInfo.status == 3"/>
                                <CheckmarkOutline v-show="project.projectBaseInfo.status == 4"/>
                            </n-icon>
                        </template>
                            {{statusInt2String[project.projectBaseInfo.status]}}
                        <template #suffix>
                            <n-popconfirm :show-icon="false" placement="bottom"
                            positive-text="确认" negative-text="取消" @positive-click="handleConfirmClick">
                                <template #trigger>
                                    <n-button size="tiny" secondary strong v-show="project.projectBaseInfo.status == 3"
                                    v-has-role="'CommitExamineProject'">
                                        确认项目
                                    </n-button>
                                </template>
                                是否确认考核结果？确认后将无法更改和撤销！
                            </n-popconfirm>

                        </template>
                    </n-statistic>
                </div>
            </div>
            <div class="tabs" v-if="!projectSpinning">
                <n-tabs size="large" style="transform:translateY(2px);" @update:value="handleTabUpdate">
                    <n-tab name="详情概览">
                        详情概览
                    </n-tab>
                    <n-tab name="项目评价" v-has-role="'RemarkExamineProject'">
                        项目评价
                    </n-tab>
                    <!-- <n-tab name="设置">
                        设置
                    </n-tab> -->
                </n-tabs>
            </div>
        </div>
        <div class="mainContent" v-if="!projectSpinning">
            <ProjectExamineDetail :projectId="projectId" :panelKey="tPanelKey" 
            @changeScheme="startEditproject(project.projectBaseInfo)" v-show="currentTab == '详情概览'"></projectExamineDetail>
            <ProjectRemark :projectId="projectId" v-show="currentTab == '项目评价'" :panelKey="tPanelKey" ></ProjectRemark>
        </div>
        <n-back-top :right="50" />
        <n-modal v-model:show="reviewProjectVisiable" preset="card" title="更改评估方案" style="width:500px">
            <template #header>
                <div>更改评估方案</div>
            </template>
                <n-form
                    label-placement="left"
                    label-width="auto"
                    require-mark-placement="right-hanging"
                    v-has-role="'StartExamineProject'"
                >
                    <n-form-item path="userName" label="评估方案">
                        <n-select v-model:value="projectInfo.value.schemeId" :options="schemeList" :on-update:value="calculateProject"
                        placeholder="请选择评估方案"/>
                    </n-form-item>
                </n-form>
        </n-modal>
    </div>
</template>

<script>
import { inject, onMounted, getCurrentInstance } from '@vue/runtime-core'
import { h, ref, computed,reactive } from "vue";
import { useMessage } from 'naive-ui'
import moment from "moment";
import ProjectExamineDetail from "../../components/project-examine-detail.vue";
import ProjectRemark from "../../components/project-remark.vue";
import {
    AlarmSharp,
    FileTrayFullSharp,
    CheckmarkOutline,
    HelpOutline
} from "@vicons/ionicons5";
export default {
    components:{
        AlarmSharp,FileTrayFullSharp,ProjectExamineDetail,ProjectRemark,CheckmarkOutline,HelpOutline
    },
    props:{
        panelKey:String
    },
    setup(props, context){
        const tPanelKey = ref(props.panelKey)
        const tabManager = inject("FreeTabs");
        const $axios = inject('$axios');
        const message = useMessage();
        const reload = inject('reload');
        const $baseURL = getCurrentInstance()?.appContext.config.globalProperties.$baseURL;
        const statusInt2String = ["待开始","进行中","待审核","待确认","已结项"];
        const importantDegreeInt2String = [
            {
                name:"低",
                tagType:"success"
            },
            {
                name:"中",
                tagType:"info"
            },
            {
                name:"高",
                tagType:"warning"
            },
            {
                name:"紧急",
                tagType:"error"
            }
        ];
        const project = ref({
        });
        const projectSpinning = ref(true);
        const projectId = ref(undefined);
        const projectInfo = reactive({
            value:{
                costTimeScore:'无',
                costTimeWeight:'无',
                onTimeScore:'无',
                onTimeWeight:'无',
                defectScore:'无',
                defectWeight:'无',
                schemeId:1,
                hasScore:false,
                projectId:undefined,
                message:undefined,
                additionScore:undefined
            }
        });
        let reviewProjectVisiable = ref(false);
        let schemeList = ref([]);
        let currentTab = ref("详情概览")

        //获取并处理基本项目信息
        function getProjectBasicInfo(projectId){
            projectSpinning.value = true;
            $axios.get($baseURL + '/project/getProjectDetailById?projectId=' + projectId).then(res=>{
                // console.log(res.data);
                if(res.data.respCode == 200){
                    //节流，防止请求过快！
                    setTimeout(()=>{
                        project.value = res.data.data;
                        //处理一堆本该由后端处理的基础数据
                        let timeAll = 0;
                        for(let item of project.value.tasks){
                            timeAll += item.costTime;
                        }
                        project.value.projectBaseInfo.timeAll = timeAll;
                        project.value.projectBaseInfo.actualTime = 
                            Math.ceil((moment(moment.utc(project.value.projectBaseInfo.actualEndTime)) - 
                            moment(moment.utc(project.value.projectBaseInfo.actualStartTime)))
                            /1000/60/60/24)
                        project.value.projectBaseInfo.scheduledTime = 
                            Math.ceil((moment(moment.utc(project.value.projectBaseInfo.scheduledEndTime)) - 
                            moment(moment.utc(project.value.projectBaseInfo.scheduledStartTime)))
                            /1000/60/60/24)

                        //修改tab标题
                        tabManager.self(props.panelKey).changeTabName(project.value.projectBaseInfo.projectName)
                    },500);
                } else {
                    message.error("获取项目详细信息失败")
                }
            }).catch(err=>{
                // console.log(err);
                message.error('请求失败');
            }).then(()=>{
                projectSpinning.value = false;
            })
        }

        function handleConfirmClick(){
            $axios.post($baseURL + '/project/commitProject',{
                projectId:project.value.projectBaseInfo.projectId
            }).then(res=>{
                if(res.data.respCode == 200){
                    message.success("确认成功");
                    reload();
                }
            }).catch(err=>{
                console.log(err);
                message.error("确认失败")
            })
        }

        function startEditproject(project){
            if(project.status == 4) return;
            getAllSchemes(project.projectClassId);
            reviewProjectVisiable.value = true;
            projectInfo.value ={
                costTimeScore:'无',
                costTimeWeight:'无',
                onTimeScore:'无',
                onTimeWeight:'无',
                defectScore:'无',
                defectWeight:'无',
                schemeId:undefined,
                hasScore:false,
                projectId:undefined,
                message:undefined,
                additionScore:undefined
            }
            projectInfo.value.projectId = project.projectId;
        }
        function getAllSchemes(id){
            $axios.get($baseURL + '/assessmentScheme/getSchemeByProjectClassId?projectClassId=' + id)
            .then(res=>{
                if(res.data.respCode == 200){
                    schemeList.value=[];
                    for(let item of res.data.data){
                        schemeList.value.push({
                            label:item.schemeName +' ' + item.schemeVersion,
                            value:item.schemeId,
                        })
                    }
                    console.log(schemeList.value)
                }
            }).catch(err=>{
                console.log(err);
                message.error("获取考核方案失败")
            })
        }

        function calculateProject(schemeId){
            // console.log(schemeId)
            $axios.get($baseURL + '/projectScore/calculateProjectScoreBySchemeId',{
                params:{
                    projectId:projectInfo.value.projectId,
                    schemeId:schemeId,
                    review_user_id:2
                }
            }).then(res=>{
                // console.log(res.data.respCode)
                if(res.data.respCode == 200){
                    console.log(res.data);
                    reviewProjectVisiable.value = false;
                    reload();
                    // console.log(projectInfo.value)
                }
            }).catch(err=>{
                message.error("计算分数失败")
            })
        }

        function reviewProject(){
            $axios.post($baseURL + '/projectScore/commitReviewProject',{
                additionScore:60,
                message:"",
                reviewUserId:2,
                projectId:projectInfo.value.projectId
            }).then(res=>{
                console.log(res);
                if(res.data.respCode == 200){
                    message.success("更改成功")
                    reviewProjectVisiable.value = false;
                    reload();
                }
            }).catch(err=>{
                console.log(err);
                message.error("更改失败")
            })
        }

        function handleTabUpdate(name){
            currentTab.value = name;
        }

        onMounted(()=>{
            projectId.value = tabManager.self(props.panelKey).params().id;
            getProjectBasicInfo(projectId.value);
        })
        return{
            tabManager,project,projectSpinning,
            importantDegreeInt2String,projectId,
            moment,statusInt2String,tPanelKey,
            startEditproject,calculateProject,reviewProject,projectInfo,reviewProjectVisiable,schemeList,
            handleTabUpdate,currentTab,handleConfirmClick
        }
    }
}
</script>

<style scoped lang="scss">
    .outer{
        width:100%;
        height:100%;
        background-color: #f7f8fa !important;
        position:relative;
        .topBar{
            width:calc(100% - 48px);
            height:110px;
            background-color:white;
            padding:24px;
            padding-bottom:0;
            border-bottom:1px solid #dadfe6;
            box-sizing: content-box;
            position:sticky;
            top: -85px;
            z-index:1;
            .infoBarSkeleton{
                display:flex;
                justify-content:space-between;
                height:65px;
                .tit{
                    display:flex;
                }
                .info{
                    display:flex;
                }
            }
            .infoBar{
                display:flex;
                justify-content:space-between;
                width:100%;
                .tit{

                    p.projectName{
                        font-size:20px;
                        font-weight: bold;
                        margin:0;
                        color:#202d40;
                        height:32px;
                    }
                    p.projectType{
                        font-size:14px;
                        height:15px;
                        color:#606c80;
                        margin:0;
                    }
                }
                .info{
                    display:flex;
                }
            }
            .tabs{

            }
        }
        .mainContent{
            margin-top:10px;
            width:100%;
            background-color:white;
            overflow-x:hidden;
        }
    }
</style>