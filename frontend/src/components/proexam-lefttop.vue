<template>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <div class="outer-box">
    <!-- 项目总览 -->
    <div class="score-box">
        <div class="score-box-top">
            <div class="total-score-box">
                <div class="score-left-top">{{assessment_score}}</div>
                <div class="score-left-bottom">{{total_score}}</div>
            </div>
            <img class="collection-right" src="https://img.codesocean.top/image/1656658619801">
        </div>
        <div class="score-box-bottom">
            <div class="chart-box">
                <div id="assessmentMychart" class="mychart" :style="{ width: '100%', height: '200%' }"></div>
            </div>
        </div>
    </div>
    <!-- 项目完成及时率 -->
    <div class="rate-data-box">
        <div class="top-box">
            <div class="box-left">
                <img src="https://img.codesocean.top/image/1656765159557" class="img-left">
                <div class="text-middle">{{procomplete_score}}</div>
            </div>
            
            <img src="https://img.codesocean.top/image/1656666073946" class="img-right">
        </div> 
        <div class="bottom-box">
            <div class="chart-box">
                <div id="completeMychart" class="mychart" :style="{ width: '100%', height: '230%' }"></div>
            </div>
        </div>
    </div>

    <!-- 项目缺陷率 -->
    <div class="project-defect-box">
        <div class="top-box">
            <div class="box-left">
                <img src="https://img.codesocean.top/image/1656767248869" class="img-left">
                <div class="text-middle">{{prodefect_score}}</div>
            </div>
            <img src="https://img.codesocean.top/image/1656767019414" class="img-right">
        </div> 
        <div class="bottom-box">
            <div class="chart-box">
                <div id="defectMychart" class="mychart" :style="{ width: '100%', height: '230%' }"></div>
            </div>
        </div>
    </div>

    <!-- 人才成本使用率 -->
    <div class="Labor-cost-box">
        <div class="top-box">
            <div class="box-left">
                <img src="https://img.codesocean.top/image/1656767435134" class="img-left">
                <div class="text-middle">{{laborcost_score}}</div>
            </div>
            <img src="https://img.codesocean.top/image/1656767142879" class="img-right">
        </div>
        <div class="bottom-box">
            <div class="chart-box">
                <div id="laborcostchart" class="mychart" :style="{ width: '100%', height: '230%' }"></div>
            </div>
        </div>
    </div>
    

  </div>
</template>

<script>

import { directive } from '@babel/types';
import { getCurrentInstance, onMounted,inject} from '@vue/runtime-core'
import { pxfy } from "seemly";
import { h, reactive, ref, computed} from "vue";
export default {
    setup(){
        // axios引入
        const $axios = inject('$axios');
        const $baseURL = getCurrentInstance()?.appContext.config.globalProperties.$baseURL;

        const assessment_score = ref('考核总分')
        const total_score = ref(86.9)
        const procomplete_score = ref('项目完成及时率')
        const prodefect_score = ref('项目缺陷率')
        const laborcost_score = ref('人才成本使用率')
        let echarts = inject("ec");

        onMounted(()=>{
            //考核总分echarts
            function initChart_TotalScore(data){
                let chartDom_all = document.getElementById("assessmentMychart")
                let myChart_all = echarts.init(chartDom_all);
                let option_all = {
                    title: {
                        // text: 'Basic Radar Chart'
                    },
                    legend: {
                        data: ['Allocated Budget', 'Actual Spending']
                    },
                    radar: {
                        // shape: 'circle',
                        indicator: [
                            { name: '缺陷分', max: 100, color:'black' },
                            { name: '及时分', max: 100, color:'black' },
                            { name: '人力分', max: 100, color:'black' }
                        ],
                        startAngle: 110
                    },
                    series: [
                        {
                            name: 'Budget vs spending',
                            type: 'radar',
                            data: [
                                {
                                    value: [
                                        data.defectScore,
                                        data.onTimeScore,
                                        data.costTimeScore
                                    ]
                                }
                            ]
                        }
                    ]
                };
                myChart_all.setOption(option_all);
            }

            $axios.get($baseURL + '/projectScore/calculateProjectScoreBySchemeId?projectId=2&review_user_id=1&schemeId=1').then(res=>{
                console.log(res);
                // console.log(option_all.series[0].data)
                if(res.data.respCode == 200){

                    console.log(res.data.data);

                    initChart_TotalScore(res.data.data);
                    
                    for(let item of res.data.data.parameterList){
                        // console.log(item)
                        // basis_data.data(item)
                    }
                } else {
                    message.error("获取项目列表失败")
                }
            }).catch(err=>{
                console.log(err);
                // message.error('请求失败');
            }).then(()=>{
                // console.log('222')
                // projectSpinning.value = false;
            })
            
            
            
            

            //项目完成及时率echarts

            var chartDom_complete = document.getElementById('completeMychart');
            var myChart_complete = echarts.init(chartDom_complete);
            var option_complete;

            option_complete = {
                title: [
                    {
                    
                    }
                ],
                color: ["#fd775a", "#fbe1a5"],
                polar: {
                    radius: [30, '80%']
                },
                angleAxis: {
                    max: 100,
                    startAngle: 75,
                    // axisLine: {
                    //   show: false
                    // }
                    axisTick: {
                    show: false
                    },
                    axisLabel: {
                    show: false
                    }
                },
                radiusAxis: {
                    type: 'category',
                    data: ['']
                },
                axisLabel: {
                    axisTick: {
                    show: false
                    }
                },
                tooltip: {},
                series: [{
                    type: 'bar',
                    name: "权重",
                    data: [{
                    value: 30,
                    itemStyle: {
                        color:" #fc5531",
                        opacity: 0.8
                    }
                    }],
                    // roundCap: true,
                    coordinateSystem: 'polar',
                    label: {
                        show: true,
                        position: 'middle',
                        formatter: '权重: {c}%',
                        fontSize: 10,
                    },
                },{
                    type: 'bar',
                    name: "及时分",
                    data: [{
                    value: -79.6,
                    itemStyle: {
                        color:" #fbe1a5"
                    },
                    }],
                    // roundCap: true,
                    coordinateSystem: 'polar',
                    label: {
                        show: true,
                        position: 'middle',
                        formatter: '及时分: {c}',
                        fontSize: 10,
                    }
                }],
                legend: {
                    show: true,
                    data: ['权重', '及时分']
                }
            };

            option_complete && myChart_complete.setOption(option_complete);
            
            //项目缺陷率
            
            var chartDom_defect = document.getElementById('defectMychart');
            var myChart_defect = echarts.init(chartDom_defect);
            var option_complete;

            option_complete = {
                title: [
                    {
                        
                    }
                ],
                color: ["rgb(186, 240, 255)", "#72d5f8"],
                polar: {
                    radius: [30, '80%']
                },
                angleAxis: {
                    max: 100,
                    startAngle: 75,
                    // axisLine: {
                    //   show: false
                    // }
                    axisTick: {
                    show: false
                    },
                    axisLabel: {
                    show: false
                    }
                },
                radiusAxis: {
                    type: 'category',
                    data: ['']
                },
                axisLabel: {
                    axisTick: {
                    show: false
                    }
                },
                tooltip: {},
                series: [{
                    type: 'bar',
                    name: "权重",
                    data: [{
                    value: 30,
                    itemStyle: {
                        color:" rgb(186, 240, 255)",
                        opacity: 0.8
                    }
                    }],
                    // roundCap: true,
                    coordinateSystem: 'polar',
                    label: {
                        show: true,
                        position: 'middle',
                        formatter: '权重: {c}%',
                        fontSize: 10,
                    },
                },{
                    type: 'bar',
                    name: "缺陷分",
                    data: [{
                    value: 79.6,
                    itemStyle: {
                        color:" #72d5f8"
                    },
                    }],
                    // roundCap: true,
                    coordinateSystem: 'polar',
                    label: {
                        show: true,
                        position: 'middle',
                        formatter: '缺陷分: {c}',
                        fontSize: 10,
                    }
                }],
                legend: {
                    show: true,
                    data: ['权重', '缺陷分']
                }
            };

            option_complete && myChart_defect.setOption(option_complete);
            
            //项目缺陷率

            var chartDom_laborcost = document.getElementById('laborcostchart');
            var myChart_laborcost = echarts.init(chartDom_laborcost);
            var option_complete;

            option_complete = {
                title: [
                    {
                    
                    }
                ],
                color: ["rgb(166, 248, 219)", "#81fbae"],
                polar: {
                    radius: [30, '80%']
                },
                angleAxis: {
                    max: 100,
                    startAngle: 75,
                    // axisLine: {
                    //   show: false
                    // }
                    axisTick: {
                    show: false
                    },
                    axisLabel: {
                    show: false
                    }
                },
                radiusAxis: {
                    type: 'category',
                    data: ['']
                },
                axisLabel: {
                    axisTick: {
                    show: false
                    }
                },
                tooltip: {},
                series: [{
                    type: 'bar',
                    name: "权重",
                    data: [{
                    value: 30,
                    itemStyle: {
                        color:" rgb(166, 248, 219)",
                        opacity: 0.8
                    }
                    }],
                    // roundCap: true,
                    coordinateSystem: 'polar',
                    label: {
                        show: true,
                        position: 'middle',
                        formatter: '权重: {c}%',
                        fontSize: 10,
                    },
                },{
                    type: 'bar',
                    name: "人力分",
                    data: [{
                    value: 79.6,
                    itemStyle: {
                        color:" #81fbae"
                    },
                    }],
                    // roundCap: true,
                    coordinateSystem: 'polar',
                    label: {
                        show: true,
                        position: 'middle',
                        formatter: '人力分: {c}',
                        fontSize: 10,
                    }
                }],
                legend: {
                    show: true,
                    data: ['权重', '人力分']
                }
            };

            option_complete && myChart_laborcost.setOption(option_complete);
        
            // window.onresize = function () {
            //     //  根据窗口大小调整曲线大小
            //     this.myChart_all.resize();
            //     this.myChart_complete.resize();
            //     this.myChart_defect.resize();
            //     this.myChart_laborcost.resize();
            // };

            window.addEventListener("resize", function() {                
                myChart_all.resize();
                myChart_complete.resize();
                myChart_defect.resize();
                myChart_laborcost.resize();          
            });
            
        })

        return{
            total_score,
            assessment_score,
            procomplete_score,
            prodefect_score,
            laborcost_score
        }
    }
}
</script>

<style lang="scss" scoped>
.outer-box{
    margin-top: 10px;
    width: 24%;
    flex-wrap: wrap;
    display: flex;
    justify-content: center;
    .score-box{
        // margin-left: 5%;
        margin-top: 10px;
        width: 80%;
        height: 340px;
        background-color: #ffffff;
        box-shadow: 0px 0px 15px  #e9e9e9;
        .score-box-top{
            display: flex;
            justify-content: space-between;
            .total-score-box{
                margin-left: 25px;
                margin-top: 20px;
                width: 100px;
                .score-left-top{
                    font-size: 18px;
                    font-weight: bold;
                }
                .score-left-bottom{
                    font-size: 35px;
                    font-weight: bold;
                    color:#0467d7;
                }
            }
            .collection-right{
                width: 120px;
                margin-right: -15px;
                margin-top: -11px;
            }
        }
        .score-box-bottom{
            // background-color: rgb(239, 146, 146);
            display: flex;
            justify-content: center;
            align-items: center;
            .chart-box{
                // background-color: rgb(245, 196, 196);
                width: 100%;
                display: flex;
                justify-content: center;
                align-items: center;
                .mychart{
                    // background-color: rgb(244, 228, 228);
                    padding-right: 10px;
                    // margin-top: 50px;
                }
            }
        }
    }
    // 项目完成及时率
    .rate-data-box{
        // margin-left: 5%;
        margin-top: 20px;
        width: 80%;
        height: 340px;
        display: flex;
        flex-wrap: wrap;
        .top-box{
            width: 100%;
            height: 70px;
            box-shadow: 0px 0px 15px  #e9e9e9;
            background-color: #ffffff;
            display: flex;
            align-items: center;
            justify-content: space-between;
            .box-left{
                display: flex;

                .img-left{
                    width: 30px;
                    height: 30px;
                    margin-left: 5px;
                }
                .text-middle{
                    font-size: 130%;
                    margin-left: 5px;
                }
            }
            
            .img-right{
                margin-top: -15px;
                width: 55px;
                height: 55px;
            }
        }
        .bottom-box{
            margin-top: 15px;
            box-shadow: 0px 0px 15px  #e9e9e9;
            background-color: #fff;
            width: 100%;
            height: 250px;
            .chart-box{
                // background-color: rgb(240, 196, 196);
                margin-top: 20px;
                display: flex;
                justify-content: center;
                .mychart{
                    
                    // background-color: rgb(196, 152, 152);
                }
            }
        }
    }
    //项目缺陷率
    .project-defect-box{
        // margin-left: 5%;
        margin-top: 20px;
        width: 80%;
        height: 340px;
        display: flex;
        flex-wrap: wrap;
        .top-box{
            width: 100%;
            height: 70px;
            box-shadow: 0px 0px 15px  #e9e9e9;
            background-color: #ffffff;
            display: flex;
            align-items: center;
            justify-content: space-between;

            .box-left{
                display: flex;

                .img-left{
                    width: 30px;
                    height: 30px;
                    margin-left: 5px;
                }
                .text-middle{
                    font-size: 130%;
                    margin-left: 5px;
                }
            }
            .img-right{
                margin-top: -15px;
                width: 55px;
                height: 55px;
            }
        }
        .bottom-box{
            margin-top: 15px;
            box-shadow: 0px 0px 15px  #e9e9e9;
            background-color: #fff;
            width: 100%;
            height: 250px;
            .chart-box{
                // background-color: rgb(240, 196, 196);
                margin-top: 20px;
                display: flex;
                justify-content: center;
                .mychart{
                    
                    // background-color: rgb(196, 152, 152);
                }
            }
        }
    }
    //人才成本使用率
    .Labor-cost-box{
        // margin-left: 5%;
        margin-top: 480px;
        width: 80%;
        height: 340px;
        display: flex;
        flex-wrap: wrap;
        padding-bottom: 20px;
        .top-box{
            width: 100%;
            height: 70px;
            box-shadow: 0px 0px 15px  #e9e9e9;
            background-color: #ffffff;
            display: flex;
            align-items: center;
            justify-content: space-between;

            .box-left{
                display: flex;

                .img-left{
                    width: 30px;
                    height: 30px;
                    margin-left: 5px;
                }
                .text-middle{
                    font-size: 130%;
                    margin-left: 5px;
                }
            }
            .img-right{
                margin-top: -15px;
                width: 55px;
                height: 55px;
            }
        }
        .bottom-box{
            margin-top: 15px;
            box-shadow: 0px 0px 15px  #e9e9e9;
            background-color: #fff;
            width: 100%;
            height: 250px;
            .chart-box{
                // background-color: rgb(240, 196, 196);
                margin-top: 20px;
                display: flex;
                justify-content: center;
                .mychart{
                    
                    // background-color: rgb(196, 152, 152);
                }
            }
        }
    }
}
/*宽度小于1800px时为以下内容*/
@media screen and (max-width:1800px){
    .outer-box{
        width: 24%;
        .score-box{
            #assessmentMychart{
                width: 100px;
                height: 30px;
            }
        }
        .rate-data-box{

        }
        .project-defect-box{

        }
        .Labor-cost-box{

        }
    }
}
/*宽度小于1350px时为以下内容*/
@media screen and (max-width:1350px){
    .outer-box{
        overflow: auto;
        width: 1000px;
        padding-right: 20px;
        .score-box{
            margin-left: 5%;
            width: 20%;
        }
        .rate-data-box{
            margin-left: 5%;
            width: 20%;
            margin-top: 13px;
        }
        .project-defect-box{
            margin-left: 5%;
            width: 20%;
            margin-top: 13px;
        }
        .Labor-cost-box{
            margin-left: 5%;
            width: 20%;
            margin-top: 13px;
        }
    }
}
</style>