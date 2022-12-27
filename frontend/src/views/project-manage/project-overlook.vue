<template>
<n-spin :show="initializeLoadingCount < 3" size="large">
    <template #icon>
    </template>
    <div class="outer" id="drawer-target">
        <div class="outer-box">
            <div class="topBtns">
                <n-button-group>
                    <n-button text type="primary" size="large" @click="activate('top')">
                        <template #icon>
                        <n-icon>
                            <FilterOutline />
                        </n-icon>
                        </template>
                        <p>项目筛选</p>
                    </n-button>
                </n-button-group>
                <div class="right">
                    <n-button text type="primary" size="large" @click="tabManager.selfActive(panelKey).toggleFullScreen()">
                        <template #icon>
                        <n-icon>
                            <expand-outline />
                        </n-icon>
                        </template>
                        <p>演示模式</p>
                    </n-button>
                </div>
            </div>
        </div>
        <n-drawer 
            v-model:show="active"
            :height="520"
            :placement="placement"
            :trap-focus="false"
            :block-scroll="false"
            to="#drawer-target"
        >
            <n-drawer-content title="项目筛选" class="drawer-box">
                <n-form
                inline
                :label-width="90"
                :model="projectFilter"
                style="display:flex;flex-wrap: wrap;postion:relative"
            >
                <n-form-item label="类型" label-placement="left">
                    <n-select v-model:value="projectFilter.projectClassId" :options="projectClassesList" placeholder="选择项目类型" 
                    style="width:200px;" clearable/>
                </n-form-item>
                <n-form-item label="负责人" label-placement="left" >
                    <n-select
                        :options="projectMasterUsersList"
                        :render-label="projectMasterRenderLabel"
                        :render-tag="projectMasterRenderSingleSelectTag"
                        v-model="projectFilter.userId"
                        filterable
                        placeholder="选择负责人"
                        clearable
                    />
                </n-form-item>
                <n-form-item label="重要程度" label-placement="left">
                    <n-select v-model="projectFilter.importantDegree" :options="projectImportantDegreeList" placeholder="选择重要程度"  
                    style="width:200px;" clearable/>
                </n-form-item>
                <n-form-item label="计划开始时间" label-placement="left">
                    <n-date-picker v-model="projectFilter.scheduledStartTime" type="datetime" clearable placeholder="计划开始时间"  style="width:200px;"/>
                </n-form-item>
                <n-form-item label="计划结束时间" label-placement="left">
                    <n-date-picker v-model="projectFilter.scheduledEndTime" type="datetime" clearable placeholder="计划结束时间"  style="width:200px;"/>
                </n-form-item>
                <n-form-item label="实际开始时间" label-placement="left">
                    <n-date-picker v-model="projectFilter.actualStartTime" type="datetime" clearable placeholder="计划开始时间"  style="width:200px;"/>
                </n-form-item>
                <n-form-item label="实际结束时间" label-placement="left">
                    <n-date-picker v-model="projectFilter.actualEndTime" type="datetime" clearable placeholder="计划结束时间"  style="width:200px;"/>
                </n-form-item>
                <n-form-item label="最低得分" label-placement="left" >
                    <n-input v-model="projectFilter.minScore" placeholder="最低得分" clearable />
                </n-form-item>
                <n-form-item label="最高得分" label-placement="left">
                    <n-input v-model="projectFilter.maxScore" placeholder="最高得分" clearable />
                </n-form-item>
                <n-button type="info" @click="getProjectList">
                    搜索
                </n-button>
                <n-button type="info" @click="getChartInfo" style="margin-left:20px">
                    总览
                </n-button>
            </n-form>
                <!-- 两个表 -->
                <div class="form-box">
                    <div class="left-form">
                        <n-data-table
                            :columns="leftColumns"
                            :data="leftTableData"
                            :max-height="200"
                            :scroll-x="600"
                            :width="600"
                            v-model:checked-row-keys="checkedProjectKeys"
                        />
                    </div>
                    <div class="left-form">
                        <n-data-table
                            :columns="rightColumns"
                            :data="rightTableData"
                            :max-height="200"
                            :scroll-x="600"
                        />
                    </div>
                </div>
                
            </n-drawer-content>
        </n-drawer>
        <div class="detailOuter"  v-show="detailVisiable">
            <div class="onTimeDetail">
                <div class="title">
                    项目平均及时率详情
                </div>
                <div class="detailView">
                    <div class="top">
                        <div class="left">
                        <div class="subTitle">
                            平均时间控制情况
                        </div>
                        <div id="onTimeLiquidFillOverLookChart" class="mychart" style="width:100%;height:240px;transform:translate(0,20px);"></div>
                        </div>
                        <div class="line">
                        </div>
                        <div class="right">
                            <div class="subTitle">
                                项目及时率排行榜
                            </div>
                            <div id="onTimeRankChart" class="mychart" style="width:100%;height:260px;"></div>
                            <!-- <div :id="'courseChart1' + projectId" class="mychart" style="width:100%;height:170px;"></div>
                            <div :id="'courseChart2' + projectId" class="mychart" style="width:100%;height:110px;"></div> -->
                        </div>
                    </div>
                </div>
            </div>
            <div class="costTimeDetail">
                <div class="title">
                    人员成本详情
                </div>
                <div class="detailView">
                    <div class="top">
                        <div class="left">
                        <div class="subTitle">
                            人员成本控制情况
                        </div>
                        <div id="costTimeLiquidFillOverLookChart" class="mychart" style="width:100%;height:240px;transform:translate(0,20px);"></div>
                        </div>
                        <div class="line">
                        </div>
                        <div class="right">
                            <div class="subTitle">
                                任务执行情况概览图
                            </div>
                            <div id="tasksCourseOverLookChart" class="mychart" style="width:100%;height:280px;"></div>
                        </div>
                    </div>
                    <div class="bottom">
                        <div class="left">
                            <div class="subTitle">
                                最超时任务概览
                            </div>
                            <n-data-table
                                pagination-behavior-on-filter="first"
                                :columns="timeCostingColumnsOption"
                                :data="mostTimeCostingData"
                                :pagination="{pageSize:6}"
                            />
                        </div>
                        <div class="line">
                        </div>
                        <div class="right">
                            <div class="subTitle">
                                最省时任务概览
                            </div>
                            <n-data-table
                                pagination-behavior-on-filter="first"
                                :columns="timeCostingColumnsOption"
                                :data="leastTimeCostingData"
                                :pagination="{pageSize:6}"
                            />
                        </div>
                    </div>
                </div>
            </div>
            <div class="defectsDetail">
                <div class="title">
                    代码质量详情
                </div>
                <div class="detailView">
                    <div class="top">
                        <div class="left">
                        <div class="subTitle">
                            代码缺陷情况
                        </div>
                        <div id="defectsTotalOverLookChart" class="mychart" style="width:100%;height:340px;transform:translate(0,-20px);"></div>
                        </div>
                        <div class="line">
                        </div>
                        <div class="right">
                            <div class="subTitle">
                                缺陷情况概览图
                            </div>
                            <div id="defectsCourseOverLookChart" class="mychart" style="width:100%;height:280px;"></div>
                        </div>
                    </div>
                    <div class="bottom">
                        <div class="left">
                            <div class="subTitle">
                                缺陷修复者排行榜
                            </div>
                            <div id="defectsFixerOverLookChart" class="mychart" style="width:100%;height:360px;"></div>
                        </div>
                        <div class="line">
                        </div>
                        <div class="right">
                            <div class="subTitle">
                                最耗时缺陷概览
                            </div>
                            <n-data-table
                                pagination-behavior-on-filter="first"
                                :columns="timeCostingDefectsColumnsOption"
                                :data="mostTimeCostingDefectsData"
                                :pagination="{pageSize:6}"
                            />
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="empty" v-show="!detailVisiable">
            <n-empty description="尚未选择项目">
                <template #extra>
                    <n-button @click="activate('top')" type="info">
                        项目筛选
                    </n-button>
                </template>
            </n-empty>
        </div>
    </div>
    <template #description>
        <n-progress
            type="line"
            :percentage="(initializeLoadingCount / 3 * 100).toFixed(0)"
            :indicator-placement="'inside'"
            processing
            style="margin:10px 0"
        />
        <div>正在准备初始数据，请稍等片刻</div>
    </template>
</n-spin>
    
    
</template>

<script>
import { h, reactive, ref, computed,defineComponent  } from "vue";
import { getCurrentInstance, onMounted,inject, watch} from '@vue/runtime-core'
import { useMessage, NAvatar, NText} from 'naive-ui'
import { drawOnTimeLiquidFillOverLookChart } from '../../components/charts/onTimeLiquidFill-overlook';
import { drawCostTimeLiquidFillOverLookChart } from '../../components/charts/costTimeLiquidFill-overlook';
import { drawDefectsTotalOverLookChart } from '../../components/charts/defectsTotalOverLookChart';
import { drawOnTimeRankChart } from '../../components/charts/onTimeRankChart';
import { drawDefectsCourseOverLookChart } from '../../components/charts/defectsCourseOverLookChart';
import { drawDefectsFixerOverLookChart } from '../../components/charts/defectsFixerOverLookChart';
import { drawTasksCourseOverLookChart } from '../../components/charts/tasksCourseOverLookChart';
import moment from "moment";
import {
    ExpandOutline,ReaderOutline,FilterOutline
} from "@vicons/ionicons5";

export default {
    components:{
        ExpandOutline,
        ReaderOutline,
        FilterOutline,
        drawOnTimeLiquidFillOverLookChart,drawCostTimeLiquidFillOverLookChart,
        drawOnTimeRankChart,drawDefectsTotalOverLookChart,drawDefectsCourseOverLookChart,
        drawDefectsFixerOverLookChart,drawTasksCourseOverLookChart
    },
    props:{
        panelKey:String
    },
    setup() {
        const $axios = inject('$axios');
        const $baseURL = getCurrentInstance()?.appContext.config.globalProperties.$baseURL;
        const active = ref(false);
        const detailVisiable = ref(false);
        const placement = ref("right");
        const message = useMessage()
        const projectMasterUsersList = ref([]);     //  项目负责人列表
        const projectClassesList = ref([]);         //  项目类型列表
        const projectListInfo = ref({value:{}});   //  所有项目信息
        const leftTableData = ref({})             //  左表格数据
        const rightTableData = ref({})           //  右表格数据
        const selectProject=ref([])              // 被选中的项目的下标数组
        const analyzedData=ref([])              // 最终分析的数据
        const initializeLoadingCount = ref(0);
        const tabManager = inject("FreeTabs");
        function activate(place){
            active.value = true;
            placement.value = place;
        };
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
        onMounted(()=>{
            getProjectList();
            getAllProjectClasses();
            getAllProjectMasterUsers();
        });
        function getProjectList(){
            $axios.get($baseURL + '/project/getProjectListByLimit',{
                params:projectFilter.value
            }).then(res=>{
                // console.log(res);
                if(res.data.respCode == 200){
                   projectListInfo.value = res.data.data
                   leftTableData.value=[]
                   rightTableData.value=[]
                   selectProject.value=[]
                   analyzedData.value=[]
                   for(let i=0;i<projectListInfo.value.length;i++){
                    projectListInfo.value[i].projectBaseInfo['key'] = i
                    leftTableData.value.push(projectListInfo.value[i].projectBaseInfo)
                   }
                //    console.log(leftTableData.value)
                   initializeLoadingCount.value ++;
                } else {
                    message.error("获取项目列表失败")
                }
            }).catch(err=>{
                console.log(err+"错误");
                message.error('请求失败');
            })
        }
        function getAllProjectClasses(){
            $axios.get($baseURL + '/projectClass/getAllProjectClasses').then(res=>{
                for(let item of res.data.data){
                    projectClassesList.value.push({
                        label:item.projectClassName,
                        value:item.projectClassId,
                        disabled:(item.isDelete == 1)
                    })
                }
                initializeLoadingCount.value ++;
            }).catch(err=>{
                message.error('获取失败');
            })
        }
        function getAllProjectMasterUsers(){
            $axios.get($baseURL + '/project/getProjectMasterUsers').then(res=>{
                projectMasterUsersList.value = []
                for(let item of res.data.data){
                    projectMasterUsersList.value.push({
                        label:item.userName,
                        value:item.userId,
                        disabled:(item.isDelete == 1),
                        avatar:item.userPortrait
                    })
                }
                initializeLoadingCount.value ++;
            }).catch(err=>{
                message.error('获取失败');
            })
        }
        const createLeftColumns = () => [
            {
                type: "selection",
                fixed: "left"
            },
            {
                title: "项目ID",
                key: "projectId",
                width: 100,
                fixed: "left"
            },
            {
                title: "项目负责人",
                key: "userName",
                width: 100,
                fixed: "left"
            },
            {
                title: "项目名",
                key: "projectName",
                width: 200
            },
            {
                title: "项目类型",
                key: "projectClassName"
            }
        ];
        const createRightColumns = () => [
            {
                title: "项目ID",
                key: "projectId",
                width: 100,
                fixed: "left"
            },
            {
                title: "项目负责人",
                key: "userName",
                width: 100,
                fixed: "left"
            },
            {
                title: "项目名",
                key: "projectName",
                width: 200
            },
            {
                title: "项目类型",
                key: "projectClassName"
            }
        ];
        let timeCostingColumnsOption = ref([
                {
                    title: "任务ID",
                    key: "taskId"
                },
                {
                    title: "任务名",
                    key: "taskName"
                },
                {
                    title: "计划时间",
                    key: "planTime"
                },
                {
                    title: "实际时间",
                    key: "costTime"
                },
                {
                    title: "完成者",
                    key: "userName",
                    defaultFilterOptionValues: [],
                    filterOptions: [
                        
                    ],
                    filter(value, row) {
                        return !!~row.userName.indexOf(String(value));
                    }
                }   
        ])
        let mostTimeCostingDefectsData = ref([]);
        let timeCostingDefectsColumnsOption = ref([
            {
                title: "缺陷ID",
                key: "defectId"
            },
            {
                title: "缺陷内容",
                key: "defectName",
                ellipsis: {
                    tooltip: true
                }
            },
            {
                title: "开启时间",
                key: "defectStartTime",
                render (row) {
                    return moment(row.defectStartTime).format("YYYY-MM-DD")
                }
            },
            {
                title: "关闭时间",
                key: "defectEndTime",
                render (row) {
                    return moment(row.defectEndTime).format("YYYY-MM-DD")
                }
            },
            {
                title: "修复者",
                key: "userName",
                defaultFilterOptionValues: [],
                filterOptions: [
                    
                ],
                filter(value, row) {
                    return !!~row.userName.indexOf(String(value));
                }
            }   
        ])
        let mostTimeCostingData = ref([]);
        let leastTimeCostingData = ref([]);
        watch(selectProject,(newValue,oldValue) => {
            rightTableData.value=[]
            analyzedData.value=[]
            for(let i of newValue){
                rightTableData.value.push(leftTableData.value[i])
                analyzedData.value.push(projectListInfo.value[i])
            }
            console.log(analyzedData.value)
        })
        function getChartInfo(){
            console.log(analyzedData.value)
            drawOnTimeLiquidFillOverLookChart(analyzedData.value,'onTimeLiquidFillOverLookChart')
            drawCostTimeLiquidFillOverLookChart(analyzedData.value,'costTimeLiquidFillOverLookChart')
            drawDefectsTotalOverLookChart(analyzedData.value,'defectsTotalOverLookChart')
            drawOnTimeRankChart(analyzedData.value,'onTimeRankChart')
            drawDefectsCourseOverLookChart(analyzedData.value,'defectsCourseOverLookChart')
            drawDefectsFixerOverLookChart(analyzedData.value,'defectsFixerOverLookChart')
            drawTasksCourseOverLookChart(analyzedData.value,'tasksCourseOverLookChart')

            // 渲染人员成本Table情况
            for(let p of analyzedData.value){
                mostTimeCostingData.value = (mostTimeCostingData.value).concat(JSON.parse(JSON.stringify(p.tasks)))
            }
            mostTimeCostingData.value.sort((item1,item2)=>{
                return (item2.costTime / item2.planTime) - (item1.costTime / item1.planTime);
            })

            for(let p of analyzedData.value){
                leastTimeCostingData.value = (leastTimeCostingData.value).concat(JSON.parse(JSON.stringify(p.tasks)))
            }
            leastTimeCostingData.value.sort((item1,item2)=>{
                return - (item2.costTime / item2.planTime) + (item1.costTime / item1.planTime);
            })
            timeCostingDefectsColumnsOption.value[4].filterOptions = Array.from(new Set(mostTimeCostingData.value.map((obj,index) => {
                return obj.userName;
            }).join(",").split(',')))
            timeCostingDefectsColumnsOption.value[4].filterOptions = 
            timeCostingDefectsColumnsOption.value[4].filterOptions.map((obj,index)=>{
                return {
                    label:obj,
                    value:obj
                }
            })

            // 渲染缺陷Table情况
            for(let p of analyzedData.value){
                mostTimeCostingDefectsData.value = (mostTimeCostingDefectsData.value).concat(JSON.parse(JSON.stringify(p.defects)))
            }
            mostTimeCostingDefectsData.value.sort((item1,item2)=>{
                let startTime1 = moment(item1.defectStartTime).toDate();
                let startTime2 = moment(item2.defectStartTime).toDate();
                let endTime1 = moment(item1.defectStartTime).toDate();
                let endTime2 = moment(item2.defectEndTime).toDate();
                return - (endTime1 - startTime1) + (endTime2 - startTime2);
            })
            timeCostingColumnsOption.value[4].filterOptions = Array.from(new Set(mostTimeCostingDefectsData.value.map((obj,index) => {
                return obj.userName;
            }).join(",").split(',')))
            timeCostingColumnsOption.value[4].filterOptions = 
            timeCostingColumnsOption.value[4].filterOptions.map((obj,index)=>{
                return {
                    label:obj,
                    value:obj
                }
            })
            active.value = false;
            detailVisiable.value = true;
        }
    return {
        active,
        detailVisiable,
        placement,
        activate,analyzedData,getChartInfo,
        getProjectList,getAllProjectMasterUsers,getAllProjectClasses,projectClassesList,
        projectFilter,projectMasterUsersList,projectListInfo,createLeftColumns,createRightColumns,
        leftTableData,rightTableData,mostTimeCostingDefectsData,timeCostingColumnsOption,timeCostingDefectsColumnsOption,
        mostTimeCostingData,leastTimeCostingData,tabManager,
        leftColumns: createLeftColumns(),
        rightColumns: createRightColumns(),
        pagination: { pageSize: 10 },
        checkedProjectKeys: selectProject,initializeLoadingCount,
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
        ],
        importantDegreeInt2String : [
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
        ]
    };
  }
}
</script>

<style lang="scss" scoped>
.outer-box{
    position: relative;
    width: 100%;
    border: 1px solid rgba(128, 128, 128, 0.2);
    // display: flex;
    // align-items: center;
    // justify-content: center;
    overflow: hidden;
    .topBtns{
        margin:0 20px;
        display:flex;
        justify-content:space-between;
        width:calc(100% - 40px);
        p{
            font-size:16px;
        }
    }
}
.form-box{
    display: flex;
    .left-form{
        width: 35vw;
        margin-right: 30px;
    }
}
.detailOuter{
    padding: 15px 15px;
    background-color:#f7f8fa;
    .onTimeDetail, .costTimeDetail, .defectsDetail{
        margin-bottom:20px;
        .title{
            margin:10px 10px;
            font-size:18px;
            font-weight:bold;
            color:#202d40;
        }
        .detailView{
            width:calc(100% - 20px);
            margin:10px;
            background-color: white;
            box-shadow:
                0px 8px 20px rgba(0, 0, 0, 0.10)
            ;
            border-radius:3px;
            .subTitle{
                font-weight:normal;
                font-size:17px;
                margin:24px 0 12px 0;
            }
            .line{
                height:80%;
                width:1px;
                background-color: #dfe3e9;
            }
            .top{
                display:flex;
                align-items: center;
                width:100%;
                height:360px;
                .left{
                    width:calc(30% - 40px);
                    height:100%;
                    margin:0 20px;
                }
                .right{
                    width:calc(70% - 40px);
                    height:100%;
                    margin:0 20px;
                }
            }
            .bottom{
                width:calc(100%);
                display:flex;
                align-items: center;
                height:470px;
                .left{
                    width:calc(50% - 40px);
                    height:100%;
                    margin:0 20px;
                }
                .right{
                    width:calc(50% - 40px);
                    height:100%;
                    margin:0 20px;
                }
            }
        }
    }
}
.empty{
    width: 100%;
    display: flex;
    justify-content: center;
    font-size: 20px;
    margin-top:100px;
    color: #3c80f2;
}
</style>