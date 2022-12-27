import * as echarts from 'echarts';
import moment from "moment";
let defectsCourseChart = undefined;
function drawDefectsCourseChart(data, domId) {
    setTimeout(() => {

        // console.log(data);

        let xAxis = [];
        let totalDefects = {};
        let finishedDefects = {};
        for(let item of data.defects){
            let date = moment(moment.utc(item.defectStartTime).toDate()).format("YYYY-MM-DD");
            xAxis.push(date);
            //计入统计
            if(totalDefects[date] == undefined) 
                totalDefects[date] = 1;
            else
                totalDefects[date] ++;
            let finishDay = moment(moment.utc(item.defectEndTime).toDate()).format("YYYY-MM-DD");
            xAxis.push(finishDay);
            //计入统计
            if(finishedDefects[finishDay] == undefined) 
                finishedDefects[finishDay] = 1;
            else
                finishedDefects[finishDay] ++;
            }
        xAxis = Array.from(new Set(xAxis))
        xAxis.sort();
        let totalDefectsData = [];
        let finishedDefectsData = [];
        for(let item of xAxis){
            if(totalDefects[item] != undefined){
                totalDefectsData.push(totalDefects[item]);
            } else {
                totalDefectsData.push(0);
            }
            if(finishedDefects[item] != undefined){
                finishedDefectsData.push(finishedDefects[item]);
            } else {
                finishedDefectsData.push(0);
            }
        }
        for(let i = 0 ; i < totalDefectsData.length ; i ++){
            totalDefectsData[i] = totalDefectsData[i] + ((i == 0) ? 0 : totalDefectsData[i-1]);
            finishedDefectsData[i] = finishedDefectsData[i] + ((i == 0) ? 0 : finishedDefectsData[i-1]);
        }

        let chartDom = document.getElementById(domId);
        defectsCourseChart = echarts.init(chartDom);
        let option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            legend: {
                data: ['待解决缺陷数', '解决缺陷数']
            },
            color: ['#24374c', '#2e80df', '#e9eaeb'],
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'category',
                    boundaryGap: false,
                    data: xAxis
                }
            ],
            yAxis: [
                {
                    type: 'value'
                }
            ],
            series: [
                {
                    name: '待解决缺陷数',
                    type: 'line',
                    areaStyle: {},
                    symbol: 'none',
                    emphasis: {
                        focus: 'series'
                    },
                    data: totalDefectsData,
                    areaStyle: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                          {
                            offset: 0,
                            color: '#24374c'
                          },
                          {
                            offset: 1,
                            color: '#eeeeee'
                          }
                        ])
                    },
                },
                {
                    name: '解决缺陷数',
                    type: 'line',
                    areaStyle: {},
                    symbol: 'none',
                    emphasis: {
                        focus: 'series'
                    },
                    data: finishedDefectsData,
                    areaStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                          {
                            offset: 0,
                            color: '#2e80df'
                          },
                          {
                            offset: 1,
                            color: '#ffffff'
                          }
                        ])
                    },
                }
            ]
        };

        option && defectsCourseChart.setOption(option);
    })
}

window.addEventListener("resize", function () {
    defectsCourseChart.resize();
});

export { drawDefectsCourseChart };