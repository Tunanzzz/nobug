import * as echarts from 'echarts';
import moment from "moment";
let completeTopChart = undefined;
let completeBottomChart = undefined;
function drawCourseChart(data, domId1, domId2) {
    setTimeout(() => {
        let chartDom = document.getElementById(domId1);
        completeTopChart = echarts.init(chartDom);
        let option1;
        let scheduledStartDayStatistic = -moment.utc(data.projectBaseInfo.scheduledStartTime).toDate().getDate();
        let scheduledEndDayStatistic = -moment.utc(data.projectBaseInfo.scheduledEndTime).toDate().getDate();
        let actualStartDayStatistic = moment.utc(data.projectBaseInfo.actualStartTime).toDate().getDate();
        let actualEndDayStatistic = moment.utc(data.projectBaseInfo.actualEndTime).toDate().getDate();
        let scheduledStartDayDescription = moment(moment.utc(data.projectBaseInfo.scheduledStartTime).toDate()).format('MM月DD日');
        let scheduledEndDayDescription = moment(moment.utc(data.projectBaseInfo.scheduledEndTime).toDate()).format('MM月DD日');
        let actualStartDayDescription = moment(moment.utc(data.projectBaseInfo.actualStartTime).toDate()).format('MM月DD日');
        let actualEndDayDescription = moment(moment.utc(data.projectBaseInfo.actualEndTime).toDate()).format('MM月DD日');

        // console.log((moment.utc(data.projectBaseInfo.scheduledStartTime).toDate() - moment.utc(data.projectBaseInfo.actualStartTime).toDate())
        // /1000/60/60/24)

        option1 = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
                left: '0.5%',
                right: '0%',
                bottom: '0%',
                top: "20%",
                containLabel: true
            },
            color: ['#24374c', '#2e80df', '#e9eaeb'],
            xAxis: [
                {
                    type: 'value',
                    show: false
                }
            ],
            yAxis: [
                {
                    type: 'category',
                    axisTick: {
                        show: false
                    },
                    data: ['开始时间']
                }
            ],
            series: [
                {
                    name: '偏移量',
                    type: 'bar',
                    label: {
                        show: true,
                        position: 'inside',
                        formatter: '{@[3]}天'
                    },
                    emphasis: {
                        focus: 'series',
                    },
                    barWidth: 40,//固定柱子宽度
                    data: [
                        (moment(moment.utc(data.projectBaseInfo.actualStartTime)) - moment(moment.utc(data.projectBaseInfo.scheduledStartTime)))
                        /1000/60/60/24
                    ]
                },
                {
                    name: '实际时间',
                    type: 'bar',
                    stack: 'Total',
                    label: {
                        show: true,
                        position: 'inside',
                        formatter: actualStartDayDescription
                    },
                    emphasis: {
                        focus: 'series'
                    },
                    barWidth: 40,//固定柱子宽度
                    data: [actualStartDayStatistic]
                },
                {
                    name: '计划时间',
                    type: 'bar',
                    stack: 'Total',
                    label: {
                        show: true,
                        position: 'inside',
                        formatter: scheduledStartDayDescription
                    },
                    emphasis: {
                        focus: 'series'
                    },
                    barWidth: 40,//固定柱子宽度
                    data: [
                        scheduledStartDayStatistic]
                }
            ]
        };

        option1 && completeTopChart.setOption(option1);

        //结束时间

        chartDom = document.getElementById(domId2);
        completeBottomChart = echarts.init(chartDom);
        let option2;

        option2 = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            grid: {
                left: '0.5%',
                right: '0%',
                bottom: '0%',
                top: '0%',
                containLabel: true
            },
            xAxis: [
                {
                    type: 'value',
                    show: false
                }
            ],
            color: ['#24374c', '#2e80df', '#e9eaeb'],
            yAxis: [
                {
                    type: 'category',
                    axisTick: {
                        show: false
                    },
                    data: ['结束时间']
                }
            ],
            series: [
                {
                    name: '偏移量',
                    type: 'bar',
                    label: {
                        show: true,
                        position: 'inside',
                        formatter: '{@[3]}天'
                    },
                    emphasis: {
                        focus: 'series',
                    },
                    barWidth: 40,//固定柱子宽度
                    data: [
                        (moment.utc(data.projectBaseInfo.actualEndTime) - moment.utc(data.projectBaseInfo.scheduledEndTime))
                        /1000/60/60/24
                    ]
                },
                {
                    name: '实际时间',
                    type: 'bar',
                    stack: 'Total',
                    label: {
                        show: true,
                        position: 'inside',
                        formatter: actualEndDayDescription
                    },
                    emphasis: {
                        focus: 'series'
                    },
                    data: [actualEndDayStatistic],
                    barWidth: 40,//固定柱子宽度
                },
                {
                    name: '计划时间',
                    type: 'bar',
                    stack: 'Total',
                    label: {
                        show: true,
                        position: 'inside',
                        formatter: scheduledEndDayDescription
                    },
                    emphasis: {
                        focus: 'series'
                    },
                    data: [scheduledEndDayStatistic],
                    barWidth: 40,//固定柱子宽度
                }
            ]
        };

        option2 && completeBottomChart.setOption(option2);
    })
}

window.addEventListener("resize", function () {
    completeTopChart.resize();
    completeBottomChart.resize();
});

export { drawCourseChart };