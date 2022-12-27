import * as echarts from 'echarts';
let myChart_all = undefined;
function drawDefectsTotalOverLookChart(data,domId){
    setTimeout(()=>{
        let chartDom_all = document.getElementById(domId);
        let taskTotalCount = 0;
        let defectTotalCount = 0;
        for(let p of data){
            taskTotalCount += p.tasks.length;
            defectTotalCount += p.defects.length;
        }
        myChart_all = echarts.init(chartDom_all);
        let option_all = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross',
                    label: {
                        backgroundColor: '#6a7985'
                    }
                }
            },
            color: ['#24374c', '#2e80df', '#e9eaeb'],
            xAxis: {
              type: 'category',
              data: ['缺陷数', '任务数']
            },
            yAxis: {
              type: 'value'
            },
            series: [
              {
                data: [
                    {
                        value: defectTotalCount,
                        itemStyle: {
                          color: '#2e80df'
                        }
                    },
                    {
                        value: taskTotalCount,
                        itemStyle: {
                          color: '#24374c'
                        }
                    },
                ],
                type: 'bar'
              }
            ]
          };;
        myChart_all.setOption(option_all);
    })
}

window.addEventListener("resize", function() {                
    myChart_all.resize();
});

export {drawDefectsTotalOverLookChart};