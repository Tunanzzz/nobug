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
                                    <img style="height:12px" src="../../static/????????????/???.svg" alt="" v-show="project.projectBaseInfo.importantDegree == 0">
                                    <img style="height:12px" src="../../static/????????????/???.svg" alt="" v-show="project.projectBaseInfo.importantDegree == 1">
                                    <img style="height:12px" src="../../static/????????????/???.svg" alt="" v-show="project.projectBaseInfo.importantDegree == 2">
                                    <img style="height:12px" src="../../static/????????????/??????.svg" alt="" v-show="project.projectBaseInfo.importantDegree == 3">
                                </template>
                            </n-tag>
                            <n-tag :bordered="false" size="small">
                                {{project.projectBaseInfo.projectClassName}}
                            </n-tag>
                        </p>
                    </div>
                </div>
                <div class="info">
                    <n-statistic label="?????????" style="margin-right:40px;">
                        <template #prefix>
                        <n-icon>
                            <FileTrayFullSharp />
                        </n-icon>
                        </template>
                        <n-number-animation ref="numberAnimationInstRef" :from="0" :to="project.projectBaseInfo.timeAll" />
                        <template #suffix>
                        / {{project.projectBaseInfo.timeInDue}} <span style="font-size:14px;">??????</span>
                        <n-tag :bordered="false" size="small" type="error" style="margin-left:10px;"
                        v-show="project.projectBaseInfo.timeAll > project.projectBaseInfo.timeInDue">
                            ?????? {{((project.projectBaseInfo.timeAll - project.projectBaseInfo.timeInDue) / project.projectBaseInfo.timeInDue * 100).toFixed(0)}}%
                        </n-tag>
                        <n-tag :bordered="false" size="small" type="success" style="margin-left:10px;"
                        v-show="project.projectBaseInfo.timeAll < project.projectBaseInfo.timeInDue">
                            ?????? {{((project.projectBaseInfo.timeInDue - project.projectBaseInfo.timeAll) / project.projectBaseInfo.timeInDue * 100).toFixed(0)}}%
                        </n-tag>
                        </template>
                    </n-statistic>
                    <n-statistic label="????????????" style="margin-right:40px;">
                        <template #prefix>
                        <n-icon>
                            <AlarmSharp />
                        </n-icon>
                        </template>
                        <n-number-animation ref="numberAnimationInstRef" :from="0" :to="project.projectBaseInfo.actualTime"/>
                        <template #suffix>
                        / {{project.projectBaseInfo.scheduledTime}} <span style="font-size:14px;">???</span>
                        <n-tag :bordered="false" size="small" type="error" style="margin-left:10px;"
                        v-show="project.projectBaseInfo.actualTime > project.projectBaseInfo.scheduledTime">
                            ?????? {{((project.projectBaseInfo.actualTime - project.projectBaseInfo.scheduledTime) / project.projectBaseInfo.scheduledTime * 100).toFixed(0)}}%
                        </n-tag>
                        <n-tag :bordered="false" size="small" type="success" style="margin-left:10px;"
                        v-show="project.projectBaseInfo.actualTime < project.projectBaseInfo.scheduledTime">
                            ?????? {{((project.projectBaseInfo.scheduledTime - project.projectBaseInfo.actualTime) / project.projectBaseInfo.scheduledTime * 100).toFixed(0)}}%
                        </n-tag>
                        </template>
                    </n-statistic>
                    <n-statistic label="??????" style="margin-right:5px;">
                        <template #prefix>
                            <n-icon>
                                <HelpOutline v-show="project.projectBaseInfo.status == 3"/>
                                <CheckmarkOutline v-show="project.projectBaseInfo.status == 4"/>
                            </n-icon>
                        </template>
                            {{statusInt2String[project.projectBaseInfo.status]}}
                        <template #suffix>
                            <n-popconfirm :show-icon="false" placement="bottom"
                            positive-text="??????" negative-text="??????" @positive-click="handleConfirmClick">
                                <template #trigger>
                                    <n-button size="tiny" secondary strong v-show="project.projectBaseInfo.status == 3"
                                    v-has-role="'CommitExamineProject'">
                                        ????????????
                                    </n-button>
                                </template>
                                ???????????????????????????????????????????????????????????????
                            </n-popconfirm>

                        </template>
                    </n-statistic>
                </div>
            </div>
            <div class="tabs" v-if="!projectSpinning">
                <n-tabs size="large" style="transform:translateY(2px);" @update:value="handleTabUpdate">
                    <n-tab name="????????????">
                        ????????????
                    </n-tab>
                    <n-tab name="????????????" v-has-role="'RemarkExamineProject'">
                        ????????????
                    </n-tab>
                    <!-- <n-tab name="??????">
                        ??????
                    </n-tab> -->
                </n-tabs>
            </div>
        </div>
        <div class="mainContent" v-if="!projectSpinning">
            <ProjectExamineDetail :projectId="projectId" :panelKey="tPanelKey" 
            @changeScheme="startEditproject(project.projectBaseInfo)" v-show="currentTab == '????????????'"></projectExamineDetail>
            <ProjectRemark :projectId="projectId" v-show="currentTab == '????????????'" :panelKey="tPanelKey" ></ProjectRemark>
        </div>
        <n-back-top :right="50" />
        <n-modal v-model:show="reviewProjectVisiable" preset="card" title="??????????????????" style="width:500px">
            <template #header>
                <div>??????????????????</div>
            </template>
                <n-form
                    label-placement="left"
                    label-width="auto"
                    require-mark-placement="right-hanging"
                    v-has-role="'StartExamineProject'"
                >
                    <n-form-item path="userName" label="????????????">
                        <n-select v-model:value="projectInfo.value.schemeId" :options="schemeList" :on-update:value="calculateProject"
                        placeholder="?????????????????????"/>
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
        const statusInt2String = ["?????????","?????????","?????????","?????????","?????????"];
        const importantDegreeInt2String = [
            {
                name:"???",
                tagType:"success"
            },
            {
                name:"???",
                tagType:"info"
            },
            {
                name:"???",
                tagType:"warning"
            },
            {
                name:"??????",
                tagType:"error"
            }
        ];
        const project = ref({
        });
        const projectSpinning = ref(true);
        const projectId = ref(undefined);
        const projectInfo = reactive({
            value:{
                costTimeScore:'???',
                costTimeWeight:'???',
                onTimeScore:'???',
                onTimeWeight:'???',
                defectScore:'???',
                defectWeight:'???',
                schemeId:1,
                hasScore:false,
                projectId:undefined,
                message:undefined,
                additionScore:undefined
            }
        });
        let reviewProjectVisiable = ref(false);
        let schemeList = ref([]);
        let currentTab = ref("????????????")

        //?????????????????????????????????
        function getProjectBasicInfo(projectId){
            projectSpinning.value = true;
            $axios.get($baseURL + '/project/getProjectDetailById?projectId=' + projectId).then(res=>{
                // console.log(res.data);
                if(res.data.respCode == 200){
                    //??????????????????????????????
                    setTimeout(()=>{
                        project.value = res.data.data;
                        //????????????????????????????????????????????????
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

                        //??????tab??????
                        tabManager.self(props.panelKey).changeTabName(project.value.projectBaseInfo.projectName)
                    },500);
                } else {
                    message.error("??????????????????????????????")
                }
            }).catch(err=>{
                // console.log(err);
                message.error('????????????');
            }).then(()=>{
                projectSpinning.value = false;
            })
        }

        function handleConfirmClick(){
            $axios.post($baseURL + '/project/commitProject',{
                projectId:project.value.projectBaseInfo.projectId
            }).then(res=>{
                if(res.data.respCode == 200){
                    message.success("????????????");
                    reload();
                }
            }).catch(err=>{
                console.log(err);
                message.error("????????????")
            })
        }

        function startEditproject(project){
            if(project.status == 4) return;
            getAllSchemes(project.projectClassId);
            reviewProjectVisiable.value = true;
            projectInfo.value ={
                costTimeScore:'???',
                costTimeWeight:'???',
                onTimeScore:'???',
                onTimeWeight:'???',
                defectScore:'???',
                defectWeight:'???',
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
                message.error("????????????????????????")
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
                message.error("??????????????????")
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
                    message.success("????????????")
                    reviewProjectVisiable.value = false;
                    reload();
                }
            }).catch(err=>{
                console.log(err);
                message.error("????????????")
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