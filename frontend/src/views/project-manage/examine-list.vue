<template>
    <div class="outer">
        <n-form
            inline
            :label-width="90"
            :model="projectFilter"
            style="display:flex;flex-wrap: wrap;postion:relative"
        >
            <n-form-item label="项目名" label-placement="left" clearable>
                <n-input v-model:value="projectFilter.projectName" placeholder="输入项目名" clearable />
            </n-form-item>
            <n-form-item label="类型" label-placement="left">
                <n-select v-model:value="projectFilter.projectClassId" :options="projectClassesList" placeholder="选择项目类型" 
                style="width:200px;" clearable/>
            </n-form-item>
            <n-form-item label="负责人" label-placement="left" >
                <n-select
                    :options="projectMasterUsersList"
                    :render-label="projectMasterRenderLabel"
                    :render-tag="projectMasterRenderSingleSelectTag"
                    v-model:value="projectFilter.userId"
                    filterable
                    placeholder="选择负责人"
                    clearable
                />
            </n-form-item>
            <n-form-item label="重要程度" label-placement="left">
                <n-select v-model:value="projectFilter.importantDegree" :options="projectImportantDegreeList" placeholder="选择重要程度"  
                style="width:200px;" clearable/>
            </n-form-item>
            <n-form-item label="计划开始时间" label-placement="left">
                <n-date-picker v-model:value="projectFilter.scheduledStartTime" type="datetime" clearable placeholder="计划开始时间"  style="width:200px;"/>
            </n-form-item>
             <n-form-item label="计划结束时间" label-placement="left">
                <n-date-picker v-model:value="projectFilter.scheduledEndTime" type="datetime" clearable placeholder="计划结束时间"  style="width:200px;"/>
            </n-form-item>
            <n-form-item label="实际开始时间" label-placement="left">
                <n-date-picker v-model:value="projectFilter.actualStartTime" type="datetime" clearable placeholder="计划开始时间"  style="width:200px;"/>
            </n-form-item>
            <n-form-item label="实际结束时间" label-placement="left">
                <n-date-picker v-model:value="projectFilter.actualEndTime" type="datetime" clearable placeholder="计划结束时间"  style="width:200px;"/>
            </n-form-item>
            <n-form-item label="最低得分" label-placement="left" >
                <n-input v-model:value="projectFilter.minScore" placeholder="最低得分" clearable />
            </n-form-item>
            <n-form-item label="最高得分" label-placement="left">
                <n-input v-model:value="projectFilter.maxScore" placeholder="最高得分" clearable />
            </n-form-item>
            <n-button type="info" @click="nextPage(1)">
                搜索
            </n-button>
        </n-form>
        <n-spin :show="projectSpinning">
            <n-space vertical class="projectList">
                <n-table striped>
                <thead>
                    <tr>
                        <th>项目ID</th>
                        <th>项目名</th>
                        <th>负责人</th>
                        <th>项目类型</th>
                        <th>重要程度</th>
                        <th>项目状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="project in projectList" :key="project.project_id">
                        <td>{{project.projectId}}</td>
                        <td>
                            <n-popover trigger="hover">
                                <template #trigger>
                                {{project.projectName}}
                                </template>
                                <span>项目计划时间：{{transformTimestamp(project.scheduledStartTime)}}~{{transformTimestamp(project.scheduledEndTime)}}</span>
                                <tr></tr>
                                <span>项目实际时间：{{transformTimestamp(project.actualStartTime)}}~{{transformTimestamp(project.actualEndTime)}}</span>
                            </n-popover>
                        </td>
                        <td style="display:flex; vertical-align: center;">
                            <n-avatar
                                size="small"
                                :src="project.userPortrait"
                                style="margin:1em 0;"
                                />
                            <p style="margin-left:10px; height:28px;transform:translateY(3px);">
                                {{project.userName}}
                            </p>
                        </td>
                        <td>{{project.projectClassName}}</td>
                        <td>
                            <n-tag round :bordered="false" :type="importantDegreeInt2String[project.importantDegree].tagType">
                                {{importantDegreeInt2String[project.importantDegree].name}}
                                <template #icon>
                                    <img src="../../static/紧急程度/低.svg" alt="" v-show="project.importantDegree == 0">
                                    <img src="../../static/紧急程度/中.svg" alt="" v-show="project.importantDegree == 1">
                                    <img src="../../static/紧急程度/高.svg" alt="" v-show="project.importantDegree == 2">
                                    <img src="../../static/紧急程度/紧急.svg" alt="" v-show="project.importantDegree == 3">
                                </template>
                            </n-tag>
                        </td>
                        <td>{{statusInt2String[project.status]}}</td>
                        <td style="width:100px">
                            <n-button type="info" @click="startEditproject(project)" v-show="project.status == 2"
                            v-has-role="'StartExamineProject'"> 考核 </n-button>
                            <!-- <n-button type="success" @click="startEditproject(project)" v-show="project.status > 2"> 查看 </n-button> -->
                            <!-- <n-button type="info" @click="seeExamineListDetail(project)" v-show="project.status == 2"> 审核 </n-button> -->
                            <n-button type="info" @click="seeExamineListDetail(project)" v-show="project.status > 2"
                            v-has-role="'WatchExamineProject'"> 查看 </n-button>
                        </td>
                    </tr>
                </tbody>
                </n-table>
            </n-space>
        </n-spin>
        <n-pagination v-model:page="curPage" :page-count="pageCount" style="float:right;margin:20px 0;" :on-update:page="nextPage" />
        <div class="bottom" style="height:50px;width:100%;"></div>
        <n-modal v-model:show="reviewProjectVisiable" preset="card" title="项目考核" style="width:500px">
            <template #header>
                <div>项目考核</div>
            </template>
                <n-form
                    label-placement="left"
                    label-width="auto"
                    require-mark-placement="right-hanging"
                >
                    <n-form-item path="userName" label="评估方案">
                        <n-select v-model:value="projectInfo.value.schemeId" :options="schemeList.value" :on-update:value="calculateProject"
                        placeholder="请选择评估方案"/>
                    </n-form-item>
                </n-form>
            <template #action>
                <n-button @click="reviewProjectVisiable = false"> 取消 </n-button>
                <n-button type="info" style="margin-left:10px" @click="reviewProject"> 进入考核 </n-button>
            </template>
        </n-modal>
    </div>
</template>

<script>
import { h, reactive, ref, computed } from "vue";
import { getCurrentInstance, onMounted,inject} from '@vue/runtime-core'
import { useMessage, NAvatar, NText} from 'naive-ui'
import {useRouter} from 'vue-router';
export default {
    props:{
        panelKey:String
    },
    setup(){
        const $axios = inject('$axios');
        const tabManager = inject("FreeTabs");
        const message = useMessage()
        const $baseURL = getCurrentInstance()?.appContext.config.globalProperties.$baseURL;
        const router = useRouter();
        const projectList = ref([]);
        const reload = inject('reload')
        const projectClassesList = ref([]);
        const projectMasterUsersList = ref([]);
        const schemeList = reactive({value:[]});
        const projectSpinning = ref(false);
        const reviewProjectVisiable = ref(false);
        const statusInt2String = ["待开始","进行中","待考核","待确认","已结项"];
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
        const projectFilter = ref({
            projectName:undefined,
            projectClassId:undefined,
            userId:undefined,
            importantDegree:undefined,
            scheduledStartTime:undefined,
            scheduledEndTime:undefined,
            actualStartTime:undefined,
            actualEndTime:undefined,
            minScore:undefined,
            maxScore:undefined
        });
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
        let pageCount = ref(0);
        let curPage = ref(1);

        onMounted(()=>{
            nextPage(1);
            getAllProjectClasses();
            getAllProjectMasterUsers();
        });

        function nextPage(page){
            curPage.value = page;
            projectSpinning.value = true;
            $axios.get($baseURL + '/project/getProjectListByPage?pageNum=' + page + '&pageSize=10',{
                params:projectFilter.value
            }).then(res=>{
                if(res.data.respCode == 200){
                    // console.log(res.data.data)
                    pageCount.value = Math.ceil(res.data.data.totalCount / 10);
                    projectList.value = [];
                    for(let item of res.data.data.projectList){
                        // console.log(item)
                        projectList.value.push(item)
                    }
                    // console.log(projectList)
                } else {
                    message.error("获取项目列表失败")
                }
            }).catch(err=>{
                // console.log(err);
                message.error('请求失败');
            }).then(()=>{
                projectSpinning.value = false;
            })
        }
        function transformTimestamp(timestamp) {
            let a = new Date(timestamp).getTime();
            let date = new Date(a);
            let Y = date.getFullYear() + '-';
            let M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
            let D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + '  ';
            let h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
            let m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes());
            // const s = date.getSeconds(); // 秒
            let dateString = Y + M + D + h + m;
            return dateString;
        }

        function getAllProjectMasterUsers(){
            $axios.get($baseURL + '/project/getProjectMasterUsers').then(res=>{
                // console.log(res.data.data);
                projectMasterUsersList.value = []
                for(let item of res.data.data){
                    projectMasterUsersList.value.push({
                        label:item.userName,
                        value:item.userId,
                        disabled:(item.isDelete == 1),
                        avatar:item.userPortrait
                    })
                }
                curPage.value = 1;
            }).catch(err=>{
                message.error('获取失败');
            })
        }

        function getAllProjectClasses(){
            $axios.get($baseURL + '/projectClass/getAllProjectClasses').then(res=>{
                // console.log(res.data.data);
                for(let item of res.data.data){
                    projectClassesList.value.push({
                        label:item.projectClassName,
                        value:item.projectClassId,
                        disabled:(item.isDelete == 1)
                    })
                }
            }).catch(err=>{
                message.error('获取失败');
            })
        }
        function startEditproject(project){
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
                    schemeList.value=[]
                    for(let item of res.data.data){
                        schemeList.value.push({
                            label:item.schemeName +' ' + item.schemeVersion,
                            value:item.schemeId,
                        })
                    }
                    // console.log(schemeList.value)
                }
            }).catch(err=>{
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
                    projectInfo.value.hasScore = true;
                    projectInfo.value.schemeId = schemeId;
                    projectInfo.value.costTimeScore = res.data.data.costTimeScore;
                    projectInfo.value.onTimeScore = res.data.data.onTimeScore;
                    projectInfo.value.defectScore = res.data.data.defectScore;
                    for(let item of res.data.data.parameterList){
                        if(item.parameterName == "项目完成及时率"){
                            projectInfo.value.onTimeWeight = item.parameterWeight
                        }else if(item.parameterName == "人员成本"){
                            projectInfo.value.costTimeWeight = item.parameterWeight
                        }else if(item.parameterName == "代码质量"){
                            projectInfo.value.defectWeight = item.parameterWeight
                        }
                    }
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
                if(res.data.respCode == 200){
                    nextPage(1);
                    setTimeout(()=>{
                        tabManager.addTabPage('examineDetail',"ID" + projectInfo.value.projectId,undefined,undefined,{'id':projectInfo.value.projectId});
                        reviewProjectVisiable.value = false;
                    },500);
                }
            }).catch(err=>{
                console.log(err);
                message.error("考核失败")
            })
        }

        function seeExamineListDetail(project){
            tabManager.addTabPage('examineDetail',"ID" + project.projectId,undefined,undefined,{'id':project.projectId});
        }

        return{
            projectList,curPage,pageCount,projectSpinning,getAllSchemes,projectInfo,calculateProject,reviewProject,
            statusInt2String,importantDegreeInt2String,projectFilter,nextPage,schemeList,reviewProjectVisiable,startEditproject ,
            projectClassesList,projectMasterUsersList,tabManager,seeExamineListDetail,transformTimestamp,
            projectMasterRenderLabel(option){
                return h("div", {
                    style: {
                    display: "flex",
                    alignItems: "center"
                    }
                }, [
                    h(NAvatar, {
                    src: option.avatar,
                    round: true,
                    size: "small"
                    }),
                    h("div", {
                    style: {
                        marginLeft: "12px",
                        padding: "4px 0"
                    }
                    }, [
                    h("div", null, [option.label])
                    ])
                ]);
            },
            projectMasterRenderSingleSelectTag({ option }){
                return h("div", {
                    style: {
                    display: "flex",
                    alignItems: "center"
                    }
                }, [
                    h(NAvatar, {
                    src: option.avatar,
                    round: true,
                    size: 24,
                    style: {
                        marginRight: "12px"
                    }
                    }),
                    option.label
                ]);
            },
            projectImportantDegreeList:[
                {
                    label:"低",
                    value:0
                },
                {
                    label:"中",
                    value:1
                },
                {
                    label:"高",
                    value:2
                },
                {
                    label:"紧急",
                    value:3
                }
            ]
        }
    }
}
</script>

<style scoped>
    .outer{
        background-color:white;
        padding:20px;
       
    }
</style>