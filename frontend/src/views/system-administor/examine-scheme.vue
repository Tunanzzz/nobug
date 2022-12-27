<template>
    <div class="outer">
        <div id="drawer-target" class="outer-box">
            <n-button-group class="top-button-box">
                <n-button @click="activate('top');" type="info" class="top-button">
                    添加考核方案
                </n-button>
            </n-button-group>
        </div>
        <n-drawer
            v-model:show="active"
            :height="'90%'"
            :placement="placement"
            :trap-focus="false"
            :block-scroll="false"
            to="#drawer-target"
        >
            <n-drawer-content class="drawer-box">
                <template #header>
                    <div class="header-box">
                        <div class="header-text">新建考核方案</div>
                        <n-button type="info" @click="showModals = true">
                            提交方案
                        </n-button>
                        <n-modal
                            v-model:show="showModals"
                            :mask-closable="false"
                            preset="dialog"
                            title="确认"
                            content="你确认要提交吗？"
                            positive-text="确认"
                            negative-text="算了"
                            @positive-click="onPositiveClick"
                            @negative-click="onNegativeClick"
                        />
                    </div>
                    
                </template>
                <div class="top-box">
                    <div class="basis-bar">
                        <div class="id-box">
                            <div class="id-text">考核方案id：</div>
                            <div class="id-select">40</div>
                        </div>
                        <div class="name-box">
                            <div class="name-text">考核方案名：</div>
                            <n-space vertical class="name-select">
                                <n-input placeholder="请输入考核方案名" v-model:value="planName" autosize style="min-width: 100%" />
                            </n-space>
                        </div>
                        <div class="type-box">
                            <div class="type-text">项目类型：</div>
                            <n-space vertical class="type-select">
                                <n-select
                                v-model:value="selectedType"
                                filterable
                                placeholder="选择项目类型"
                                :options="projectClass"
                                />
                            </n-space>
                        </div>
                        <div class="head-box">
                            <div class="head-text">优先级：</div>
                            <n-space vertical class="head-select">
                                <n-select
                                v-model:value="selectedInspection"
                                filterable
                                placeholder="选择优先级"
                                :options="inspection_options"
                                />
                            </n-space>
                        </div>
                    </div>
                    
                    <div class="scheme-content">
                        <div class="top-bar">
                            <div class="left_text">方案内容:</div>
                            
                        </div>
                        <div class="change-button">
                            <n-card style="margin-bottom: 16px">
                                <n-tabs type="line" @update:value="tabUpdated">
                                <!-- 及时率板块 -->
                                <n-tab-pane name="ontime" tab="及时率" class="all-box" display-directive="show" >
                                    <!-- 左侧输入框 -->
                                    <div class="left-input">
                                        <n-form ref="formRef" :model="completeObject" :style="{ maxWidth: '640px' }">
                                            <n-form-item
                                            label="及时率所占权值"
                                            path="weight"
                                            :rule="{
                                                required: true,
                                                message: '请输入权值',
                                                trigger: ['input', 'blur']
                                            }"
                                            v-model:value="completeWeight"
                                            >
                                            <n-input v-model:value="completeObject.weight" placeholder="请输入权值" clearable />
                                            </n-form-item>
                                            <div class="form-left-box">
                                                <n-form-item
                                                v-for="(item, index) in completeObject.standard"
                                                class="form-left"
                                                :key="index"
                                                :label="`阈值${index + 1}`"
                                                :path="`standard[${index}].threshold`"
                                                :rule="{
                                                    required: true,
                                                    message: `请输入阈值${index + 1}`,
                                                    trigger: ['input', 'blur']
                                                }"
                                                >
                                                
                                                    <n-input v-model:value="item.threshold" placeholder="请输入阈值" clearable />
                                                </n-form-item>
                                            </div>
                                            <div class="form-right-box">
                                                <n-form-item
                                                v-for="(item, index) in completeObject.standard"
                                                class="form-right"
                                                :key="index"
                                                :label="`分数${index + 1}`"
                                                :path="`standard[${index}].score`"
                                                :rule="{
                                                    required: true,
                                                    message: `请输入分数${index + 1}`,
                                                    trigger: ['input', 'blur']
                                                }"
                                                >
                                                
                                                    <n-input v-model:value="item.score" placeholder="请输入分数" clearable />
                                                    <n-button style="margin-left: 12px" @click="completeRemoveItem(index)">
                                                        删除
                                                    </n-button>
                                                </n-form-item>
                                            </div>
                                            
                                            
                                            <n-form-item class="form-button">
                                            <n-space>
                                                <n-button attr-type="button" @click="handleValidateClick(completeThresholdArr,completeScoreArr,'completeChart',completeObject)">
                                                    生成
                                                </n-button>
                                                <n-button attr-type="button" @click="completeAddItem">
                                                    增加
                                                </n-button>
                                            </n-space>
                                            </n-form-item>
                                        </n-form>
                                    </div>
                                    <!-- 右侧呈现框 -->
                                    <div class="right-echart">
                                        <div id="completeChart" class="mychart" :style="{ width: '45vw', height: '34vh' }"></div>
                                    </div>
                                </n-tab-pane>
                                <!-- 缺陷率板块 -->
                                <n-tab-pane name="defects" tab="缺陷率" class="all-box" display-directive="show" >
                                    <!-- 左侧输入框 -->
                                    <div class="left-input">
                                        <n-form ref="formRef" :model="defectObject" :style="{ maxWidth: '640px' }">
                                            <n-form-item
                                            label="缺陷率所占权值"
                                            path="weight"
                                            :rule="{
                                                required: true,
                                                message: '请输入权值',
                                                trigger: ['input', 'blur']
                                            }"
                                            v-model:value="defectWeight"
                                            >
                                            <n-input v-model:value="defectObject.weight" placeholder="请输入权值" clearable />
                                            </n-form-item>
                                            <div class="form-left-box">
                                                <n-form-item
                                                v-for="(item, index) in defectObject.standard"
                                                class="form-left"
                                                :key="index"
                                                :label="`阈值${index + 1}`"
                                                :path="`standard[${index}].threshold`"
                                                :rule="{
                                                    required: true,
                                                    message: `请输入阈值${index + 1}`,
                                                    trigger: ['input', 'blur']
                                                }"
                                                >
                                                
                                                    <n-input v-model:value="item.threshold" placeholder="请输入阈值" clearable />
                                                </n-form-item>
                                            </div>
                                            <div class="form-right-box">
                                                <n-form-item
                                                v-for="(item, index) in defectObject.standard"
                                                class="form-right"
                                                :key="index"
                                                :label="`分数${index + 1}`"
                                                :path="`standard[${index}].score`"
                                                :rule="{
                                                    required: true,
                                                    message: `请输入分数${index + 1}`,
                                                    trigger: ['input', 'blur']
                                                }"
                                                >
                                                
                                                    <n-input v-model:value="item.score" placeholder="请输入分数" clearable />
                                                    <n-button style="margin-left: 12px" @click="defectRemoveItem(index)">
                                                        删除
                                                    </n-button>
                                                </n-form-item>
                                            </div>
                                            
                                            
                                            <n-form-item class="form-button">
                                            <n-space>
                                                <n-button attr-type="button" @click="handleValidateClick(defectThresholdArr,defectScoreArr,'defectChart',defectObject)">
                                                    生成
                                                </n-button>
                                                <n-button attr-type="button" @click="defectAddItem">
                                                    增加
                                                </n-button>
                                            </n-space>
                                            </n-form-item>
                                        </n-form>
                                    </div>
                                    <!-- 右侧呈现框 -->
                                    <div class="right-echart">
                                        <div id="defectChart" class="mychart" :style="{ width: '40vw', height: '34vh' }"></div>
                                    </div>
                                </n-tab-pane>
                                <!-- 人才成本使用率 -->
                                <n-tab-pane name="laborcost" tab="人才成本使用率" class="all-box" display-directive="show" >
                                    <!-- 左侧输入框 -->
                                    <div class="left-input">
                                        <n-form ref="formRef" :model="laborcostObject" :style="{ maxWidth: '640px' }">
                                            <n-form-item
                                            label="人才成本使用率所占权值"
                                            path="weight"
                                            :rule="{
                                                required: true,
                                                message: '请输入权值',
                                                trigger: ['input', 'blur']
                                            }"
                                            v-model:value="laborcostWeight"
                                            >
                                            <n-input v-model:value="laborcostObject.weight" placeholder="请输入权值" clearable />
                                            </n-form-item>
                                            <div class="form-left-box">
                                                <n-form-item
                                                v-for="(item, index) in laborcostObject.standard"
                                                class="form-left"
                                                :key="index"
                                                :label="`阈值${index + 1}`"
                                                :path="`standard[${index}].threshold`"
                                                :rule="{
                                                    required: true,
                                                    message: `请输入阈值${index + 1}`,
                                                    trigger: ['input', 'blur']
                                                }"
                                                >
                                                
                                                    <n-input v-model:value="item.threshold" placeholder="请输入阈值" clearable />
                                                </n-form-item>
                                            </div>
                                            <div class="form-right-box">
                                                <n-form-item
                                                v-for="(item, index) in laborcostObject.standard"
                                                class="form-right"
                                                :key="index"
                                                :label="`分数${index + 1}`"
                                                :path="`standard[${index}].score`"
                                                :rule="{
                                                    required: true,
                                                    message: `请输入分数${index + 1}`,
                                                    trigger: ['input', 'blur']
                                                }"
                                                >
                                                
                                                    <n-input v-model:value="item.score" placeholder="请输入分数" clearable />
                                                    <n-button style="margin-left: 12px" @click="laborcostRemoveItem(index)">
                                                        删除
                                                    </n-button>
                                                </n-form-item>
                                            </div>
                                            
                                            
                                            <n-form-item class="form-button">
                                            <n-space>
                                                <n-button attr-type="button" @click="handleValidateClick(laborcostThresholdArr,laborcostScoreArr,'costChart',laborcostObject)">
                                                    生成
                                                </n-button>
                                                <n-button attr-type="button" @click="laborcostAddItem">
                                                    增加
                                                </n-button>
                                            </n-space>
                                            </n-form-item>
                                        </n-form>
                                    </div>
                                    <!-- 右侧呈现框 -->
                                    <div class="right-echart">
                                        <div id="costChart" class="mychart" :style="{ width: '40vw', height: '34vh' }"></div>
                                    </div>
                                </n-tab-pane>
                                </n-tabs>
                            </n-card>
                        </div>
                    </div>
                </div>
            </n-drawer-content>
        </n-drawer>
        <n-spin :show="schemeSpinning">
            <n-space vertical class="schemeList">
                <n-table striped>
                <thead>
                    <tr>
                        <th>考核方案ID</th>
                        <th>考核方案名</th>
                        <th>项目类型</th>
                        <th>优先级</th>
                        <th>创建时间</th>
                        <th>方案状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="scheme in schemeList" :key="scheme.scheme_id">
                        <td>{{scheme.schemeId}}</td>
                        <td>
                            {{scheme.schemeName}}
                        </td>
                        <td>{{scheme.projectClassName}}</td>
                        <td>
                            <n-tag round :bordered="false" :type="importantDegreeInt2String[scheme.importantDegree].tagType">
                                {{importantDegreeInt2String[scheme.importantDegree].name}}
                                <template #icon>
                                    <img src="../../static/紧急程度/低.svg" alt="" v-show="scheme.importantDegree == 0">
                                    <img src="../../static/紧急程度/中.svg" alt="" v-show="scheme.importantDegree == 1">
                                    <img src="../../static/紧急程度/高.svg" alt="" v-show="scheme.importantDegree == 2">
                                    <img src="../../static/紧急程度/紧急.svg" alt="" v-show="scheme.importantDegree == 3">
                                </template>
                            </n-tag>
                        </td>
                        <td>{{moment(moment.utc(scheme.create_time).toDate()).format('YYYY-MM-DD')}}</td>
                        <td>{{(scheme.isDelete == 0)?"启用中":"禁用中"}}</td>
                        <td style="width:200px">
                            <n-popconfirm
                                @positive-click="removescheme(scheme.schemeId)"
                                positive-text="确认"
                                negative-text="取消"
                            >
                                <template #trigger>
                                    <n-button type="error" style="margin-left:10px" v-show="scheme.isDisable == 0"> 弃用方案 </n-button>
                                </template>
                                    确认弃用方案？
                            </n-popconfirm>
                            <n-popconfirm
                                @positive-click="removescheme(scheme.schemeId)"
                                positive-text="确认"
                                negative-text="取消"
                            >
                                <template #trigger>
                                    <n-button type="success" style="margin-left:10px" v-show=" scheme.isDisable == 1"> 启用方案 </n-button>
                                </template>
                                    确认启用方案？
                            </n-popconfirm>
                        </td>
                    </tr>
                </tbody>
                </n-table>
            </n-space>
        </n-spin>
        <n-pagination v-model:page="curPage" :page-count="pageCount" style="float:right;margin:20px 0;" :on-update:page="nextPage" />
        <div class="bottom" style="height:50px;width:100%;"></div>
    </div>

    
    <n-modal v-model:show="showEditschemeModal" preset="card" title="编辑用户" style="width:500px">
        <template #header>
            <div>编辑用户</div>
        </template>
            <n-form
                :model="editschemeModelRef"
                label-placement="left"
                label-width="auto"
                require-mark-placement="right-hanging"
            >
                <n-form-item path="schemeName" label="用户名">
                    <n-input v-model:value="editschemeModelRef.schemeName" @keydown.enter.prevent />
                    </n-form-item>
                <n-form-item path="roleName" label="角色">
                    <n-select v-model:value="editschemeModelRef.roles" multiple :options="rolesList" />
                </n-form-item>
            </n-form>
        <template #action>
            <n-button @click="showEditschemeModal = false"> 取消 </n-button>
            <n-button type="info" style="margin-left:10px" @click="editscheme"> 提交 </n-button>
        </template>
    </n-modal>

</template>

<script>
import { h, reactive, ref, computed } from "vue";
import { getCurrentInstance, onMounted,inject, watch} from '@vue/runtime-core'
import { useMessage } from 'naive-ui'
import moment from "moment";
import { useLoadingBar } from "naive-ui";

export default {
    props:{
        panelKey:String
    },
    setup(){
        const $axios = inject('$axios');
        const message = useMessage()
        const $baseURL = getCurrentInstance()?.appContext.config.globalProperties.$baseURL;
        const schemeList = ref([]);
        const reload = inject('reload')
        const rolesList = reactive([]);
        const schemeSpinning = ref(false);
        const projectClass = reactive([]);
        const active = ref(false);
        const placement = ref("right");
        let echarts = inject("ec");
        
        //加载条
        const loadingBar = useLoadingBar();
        const disabledRef = ref(true);

        //各权值定义
        const completeWeight = ref()
        const defectWeight = ref()
        const laborcostWeight = ref()

        // 方案题目
        const planName = ref()

        // 项目类型选项
        const selectedType = ref()

        //项目优先级选项
        const selectedInspection = ref()

        //提交框
        const messages = useMessage()
        const showModalRef = ref(false)

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
            
        ]

        let pageCount = ref(0);
        let curPage = ref(1);

        onMounted(()=>{
            nextPage(1);
            $axios.get($baseURL + '/role/getAllRoles').then(res=>{
                for(let item of res.data.data){
                    rolesList.push({
                        label:item.roleName,
                        value:item.roleId,
                        disabled:(item.isDelete == 1)
                    })
                }
            }).catch(err=>{
                message.error('获取失败');
            })
            $axios.get($baseURL + '/projectClass/getAllProjectClasses').then(res=>{
                // console.log('222',res.data.data)
                for(let item of res.data.data){
                    projectClass.push({
                        label: item.projectClassName,
                        value: item.projectClassId,
                    })
                }
                console.log('333',projectClass)
            }).catch(err=>{
                message.error('获取失败');
            })
        });

        // 横向菜单栏切换后执行函数
        function tabUpdated(ev){
            console.log(ev)
            switch(ev){
                case "ontime":
                    setTimeout(()=>{
                        setecharts( completeThresholdArr, completeScoreArr, "completeChart" );
                    })
                    break;
                
                case "defects":
                    setTimeout(()=>{
                        setecharts( laborcostThresholdArr, laborcostScoreArr, "costChart" );
                    })
                    break;
                case "laborcost":
                    setTimeout(()=>{
                        setecharts( defectThresholdArr, defectScoreArr, "defectChart" );
                    })
                    break;
                default:
            }
        }

        //方案提交post接口
        function postscheme(arr){
            $axios.post($baseURL + '/assessmentScheme/insertAssessmentScheme', {
                    assessParameterList:[
                        {
                            parameterName: "及时率",
                            parameterWeight: completeWeight.value
                        },{
                            parameterName: "缺陷率",
                            parameterWeight: defectWeight.value
                        },{
                            parameterName: "人才成本使用率",
                            parameterWeight: laborcostWeight.value
                        }
                    ],
                    importantDegree: selectedInspection.value,
                    projectClassId: selectedType.value,
                    schemeName: planName.value,
                    schemeVersion: 1.0,
                    thresholdVoList: arr
                })
                .then(function (response) {
                    messages.success('提交成功')
                    reload();
                })
                .catch(function (error) {
                    console.log(error);
                    if(error) {
                        messages.error('修改失败');
                        console.log(arr)
                    }
                });
        }

        function setecharts( xdata,ydata,domId ){
            // console.log(xdata,ydata,domId);
            // 项目完成及时率echarts
            var chartDom = document.getElementById(domId);
            // console.log(chartDom)
            var myChart = echarts.init(chartDom);
            var option;

            option = {
                tooltip: {
                    trigger: 'axis',
                    position: function (pt) {
                    return [pt[0], '10%'];
                    }
                },
                title: {
                    left: 'center'
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    data: xdata
                },
                yAxis: {
                    type: 'value'
                },

                series: [
                    {
                    name: 'Fake Data',
                    type: 'line',
                    symbol: 'none',
                    sampling: 'lttb',
                    itemStyle: {
                        color: 'rgb(25, 70, 131)'
                    },
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                        {
                            offset: 0,
                            color: '#c6ffdd'
                        },
                        {
                            offset: 0.5,
                            color: '#fbd786'
                        },
                        {
                            offset: 1,
                            color: '#f7797d'
                        }
                        ])
                    },
                    data: ydata
                    }
                ]
            };

            option && myChart.setOption(option);
        }

        function activate(place){
            active.value = true;
            console.log(active.value);
            placement.value = place;
            setTimeout(()=>{
                setecharts([0],[0],"completeChart");
            });
        };


        function nextPage(page){
            curPage.value = page;
            schemeSpinning.value = true;
            $axios.get($baseURL + '/assessmentScheme/getSchemesListByPage?pageNum=' + page + '&pageSize=10').then(res=>{
                console.log(res);
                if(res.data.respCode == 200){
                    pageCount.value = Math.ceil(res.data.data.totalCount / 10);
                    schemeList.value = [];
                    for(let item of res.data.data.schemeList){
                        schemeList.value.push(item)
                    }
                } else {
                    message.error("获取用户列表失败")
                }
            }).catch(err=>{
                console.log(err);
                message.error('请求失败');
            }).then(()=>{
                schemeSpinning.value = false;
            })
        }

        function removescheme(scheme_id){
            console.log(scheme_id)
            $axios.post($baseURL + '/assessmentScheme/disableSchemeById', {
                schemeId: scheme_id
            })
            .then(function (response) {
                console.log(response);
                message.success('禁用成功');
                reload();
            })
            .catch(function (error) {
                console.log(error);
                if(error) {
                    message.error('禁用失败');
                }
            });
        }


        let showNewschemeModal = ref(false)
        const newschemeModelRef = ref({
            schemeName: null,
            schemePassword: null
        });

        let showEditschemeModal = ref(false)
        const editschemeModelRef = ref({
            schemeName: null,
            roles: null,
            schemeId:null
        });

        // 表单函数
        const formRef = ref(null);
    
        const completeObject = reactive({
            weight: "",
            standard: [
                { threshold: "" ,score:"" }
            ]
        });
        const defectObject = reactive({
            weight: "",
            standard: [
                { threshold: "" ,score:"" }
            ]
        });
        const laborcostObject = reactive({
            weight: "",
            standard: [
                { threshold: "" ,score:"" }
            ]
        });

        //存完成及时率数组
        const completeThresholdArr = reactive([0]);
        const completeScoreArr = reactive([0]);
        
        //存缺陷率数组
        const defectThresholdArr = reactive([0]);
        const defectScoreArr = reactive([0]);

        // watch(completeObject, (newValue,oldValue) => {
        //     // console.log('watch 已触发', newValue)
        //     //调用生成
        //     handleValidateClick(completeThresholdArr,completeScoreArr,'completeChart',completeObject)
        // })
        // watch(defectObject, (newValue,oldValue) => {
        //     // console.log('watch 已触发', newValue)
        //     //调用生成
        //     handleValidateClick(defectThresholdArr,defectScoreArr,'defectChart',defectObject)
        // })
        // watch(laborcostObject, (newValue,oldValue) => {
        //     // console.log('watch 已触发', newValue)
        //     //调用生成
        //     handleValidateClick(laborcostThresholdArr,laborcostScoreArr,'costChart',laborcostObject)
        // })
        //存人才成本使用率数组
        const laborcostThresholdArr = reactive([0]);
        const laborcostScoreArr = reactive([0]);

        //删除函数
        const completeRemoveItem = (index) => {
            completeObject.standard.splice(index, 1);
        };
        const defectRemoveItem = (index) => {
            defectObject.standard.splice(index, 1);
        };
        const laborcostRemoveItem = (index) => {
            laborcostObject.standard.splice(index, 1);
        };
        
        //添加函数
        const completeAddItem = () => {
            completeObject.standard.push({ threshold: "", score:""  });
        };
        const defectAddItem = () => {
            defectObject.standard.push({ threshold: "", score:""  });
        };
        const laborcostAddItem = () => {
            laborcostObject.standard.push({ threshold: "", score:""  });
        };
        
        //生成函数
        const handleValidateClick = (thresholdArr,scoreArr,id,Object) => {
            console.log('11')
            formRef.value?.validate((errors) => {
                if (!errors) {
                    console.log(Object)
                    thresholdArr.splice(0,thresholdArr.length); //清空数组 
                    scoreArr.splice(0,completeScoreArr.length);
                    for(let i=0; i<Object.standard.length; i++){
                        console.log(Object.standard)
                        
                        thresholdArr.push(Object.standard[i].threshold)
                        scoreArr.push(Object.standard[i].score)
                    }
                    setecharts( thresholdArr, scoreArr, id )
                } else {
                console.log(errors);
                }
            });
        };
        return{
            schemeList,showNewschemeModal,newschemeModelRef,rolesList,removescheme,showEditschemeModal,curPage,pageCount,schemeSpinning,moment,
            editschemeModelRef,importantDegreeInt2String,nextPage,
            active,
            placement,
            activate,
            setecharts,
            // 关于加载条
            loadingBar,
            disabledRef,
            // 关于表单
            formRef,
            completeObject,
            completeAddItem,defectAddItem,laborcostAddItem,
            completeRemoveItem,defectRemoveItem,laborcostRemoveItem,
            handleValidateClick,

            // 用于重组
            // arr,

            // 关于新建的数组
            completeThresholdArr,
            completeScoreArr,
            defectThresholdArr,
            defectScoreArr,
            laborcostThresholdArr,
            laborcostScoreArr,

            // 各权值
            completeWeight,
            defectWeight,
            laborcostWeight,

            // 方案题目
            planName,

            // 项目类型选项
            selectedType,
            
            //项目优先级选项
            selectedInspection,
            inspection_options: [
                {
                    label: '紧急',
                    value: '3'
                },
                {
                    label: '高',
                    value: '2'
                },
                {
                    label: "中",
                    value: '1'
                },
                {
                    label: '低',
                    value: '0'
                },
            ],

            showModals: ref(false),
            tabUpdated,defectObject,laborcostObject,projectClass,
            showModals: showModalRef,
            onNegativeClick () {
                messages.success('Cancel')
                showModalRef.value = false
            },
            onPositiveClick () {
                console.log(completeScoreArr,completeThresholdArr);
                // return;
                let arr = [];
                for(let i=0 ; i< completeThresholdArr.length; i++){
                    arr.push({
                        parameterName: "及时率",
                        parameterScore: completeScoreArr[i],
                        parameterValue: completeThresholdArr[i]
                    })
                }
                for(let i=0 ; i< defectThresholdArr.length; i++){
                    arr.push({
                        parameterName: "缺陷率",
                        parameterScore: defectScoreArr[i],
                        parameterValue: defectThresholdArr[i]
                    })
                }
                for(let i=0 ; i< laborcostThresholdArr.length; i++){
                    arr.push({
                        parameterName: "人才成本使用率",
                        parameterScore: laborcostScoreArr[i],
                        parameterValue: laborcostThresholdArr[i]
                    })
                }
                console.log(arr)
                postscheme(arr)
                showModalRef.value = false
            }
        }
    }
}
</script>

<style lang="scss" scoped>
    .outer{
        background-color:white;
        padding:20px;
        .outer-box{
            margin-bottom: 20px;
        }
        .drawer-box{
            min-width: 1000px;
            .header-box{
                display: flex;
                justify-content: space-between;
                width: 80vw;
                margin-right: 30px;
                .header-text{
                    height: 30px;
                    line-height: 30px;
                }
            }
            .top-box{
                height: 100%;
                display: flex;
                flex-wrap: wrap;
                .basis-bar{
                    width: 100%;
                    height: 10%;
                    display: flex;
                    div{
                        margin-left: 3%;
                    }
                    .id-box{
                        display: flex;
                        justify-content: space-around;
                        align-items: center;
                        width: 15%;
                        height: 100%;
                        .id-text{
                            // height: 40px;
                            align-items: center;
                            line-height: 40px;
                            width: 50%;
                            font-size: 17px;
                            min-width: 100px;
                        }

                        .id-select{
                            align-items: center;
                            width: 50%;
                            margin: auto;
                            height: 40px;
                            line-height: 40px;
                            font-size: 17px;
                        }
                    }
                    .name-box{
                        display: flex;
                        justify-content: space-around;
                        align-items: center;
                        width: 25%;
                        .name-text{
                            line-height: 40px;
                            width: 20%;
                            font-size: 17px;
                            min-width: 110px;
                        }
                        .name-select{
                            width: 55%;
                            margin: auto;
                            height: 40px;
                            font-size: 17px;
                            min-width: 110px;
                        }
                    }
                    .type-box{
                        // height: 40px;
                        display: flex;
                        justify-content: space-around;
                        align-items: center;
                        width: 20%;
                    .type-text{
                            line-height: 40px;
                            font-size: 17px;
                            width: 25%;
                            min-width: 100px;
                        }
                        .type-select{
                            width: 65%;
                            margin: auto;
                            // margin-top: 13px;
                        }
                    }
                    .head-box{
                        // height: 40px;
                        display: flex;
                        justify-content: space-around;
                        align-items: center;
                        width: 20%;
                        .head-text{
                            line-height: 40px;
                            font-size: 17px;
                            width: 25%;
                            min-width: 100px;
                        }
                        .head-select{
                            width: 65%;
                            margin: auto;
                            // margin-top: 13px;
                        }
                    }

                }
                .scheme-content{
                    width: 100%;
                    height: 90%;
                    min-width: 500px;
                    display: flex;
                    flex-wrap: wrap;
                    margin: 0.5% 3.5%;
                    .top-bar{
                        align-items: center;
                        font-size: 17px;
                        height: 10%;
                        width: 100%;
                        display: flex;
                        // justify-content: space-between;
                        .left_text{
                            
                        }
                        .right-button{
                            margin-left: 50px;

                        }
                    }
                    .change-button{
                        width: 100%;
                        height: 90%;
                        margin: 1% 0;
                        display: flex;
                        
                        .all-box{
                            
                            width: 100%;
                            height: 100%;
                            display: flex;
                            align-items: center;
                            .left-input{
                                overflow: auto;
                                max-height: 40vh;
                                width: 50%;
                                align-items: center;
                                .form-left-box{
                                    width: 40%;
                                    float: left;
                                    .form-left{
                                        width: 100%;
                                        
                                    }   
                                }
                                .form-right-box{
                                    width: 50%;
                                    float: right;
                                    .form-right{
                                        width: 100%;
                                    }
                                }
                                
                                .form-button{
                                    float: left;
                                }
                            }
                            .right-echart{
                                width:50%;
                                height:200%;
                                display: flex;
                                align-items: center;
                            }
                        }
                        
                    }
                }
                
            }
            
        }
    }
    
</style>