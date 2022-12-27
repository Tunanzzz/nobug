import * as echarts from 'echarts';
import moment from "moment";
let tasksCourseChart = undefined;
function drawTasksCourseOverLookChart(data, domId) {
    setTimeout(() => {
        let xAxis = [];
        let totalTasks = {};
        let finishedTasks = {};
        for(let p of data){
            for(let item of p.tasks){
                let date = moment(moment.utc(item.taskReleaseTime).toDate()).format("YYYY-MM-DD");
                xAxis.push(date);
                //计入统计
                if(totalTasks[date] == undefined) 
                    totalTasks[date] = 1;
                else
                    totalTasks[date] ++;
                let finishDay = moment(getFinishDay(moment.utc(item.taskReleaseTime).toDate(),item.costTime)).format("YYYY-MM-DD");
                xAxis.push(finishDay);
                //计入统计
                if(finishedTasks[finishDay] == undefined) 
                    finishedTasks[finishDay] = 1;
                else
                    finishedTasks[finishDay] ++;
            }
        }
        xAxis = Array.from(new Set(xAxis))
        xAxis.sort();
        let totalTasksData = [];
        let finishedTasksData = [];
        for(let item of xAxis){
            if(totalTasks[item] != undefined){
                totalTasksData.push(totalTasks[item]);
            } else {
                totalTasksData.push(0);
            }
            if(finishedTasks[item] != undefined){
                finishedTasksData.push(finishedTasks[item]);
            } else {
                finishedTasksData.push(0);
            }
        }
        for(let i = 0 ; i < totalTasksData.length ; i ++){
            totalTasksData[i] = totalTasksData[i] + ((i == 0) ? 0 : totalTasksData[i-1]);
            finishedTasksData[i] = finishedTasksData[i] + ((i == 0) ? 0 : finishedTasksData[i-1]);
        }

        function getFinishDay(startTime,costTime){
            let time = Number(startTime);
            while(costTime > 1){
                costTime --;
                time = time + 1000*60*60;
                if(new Date(time).getHours() > 21) time = time + 1000*60*60*12;
            }
            return new Date(time);
        }

        let chartDom = document.getElementById(domId);
        tasksCourseChart = echarts.init(chartDom);
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
                data: ['总任务数', '完成任务数']
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
                    name: '总任务数',
                    type: 'line',
                    areaStyle: {},
                    symbol: 'none',
                    emphasis: {
                        focus: 'series'
                    },
                    data: totalTasksData,
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
                    name: '完成任务数',
                    type: 'line',
                    areaStyle: {},
                    symbol: 'none',
                    emphasis: {
                        focus: 'series'
                    },
                    data: finishedTasksData,
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

        option && tasksCourseChart.setOption(option);
    })
}

window.addEventListener("resize", function () {
    tasksCourseChart.resize();
});

export { drawTasksCourseOverLookChart };