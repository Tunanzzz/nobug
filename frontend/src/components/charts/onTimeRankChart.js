import * as echarts from 'echarts';
let myChart_all = undefined;
function drawOnTimeRankChart(data, domId) {
    setTimeout(() => {
        let fixerData = {};
        for(let p of data){
            let actualTime = new Date((p.projectBaseInfo.actualEndTime)).getTime() - new Date((p.projectBaseInfo.actualStartTime)).getTime() 
            let scheduledTime = new Date((p.projectBaseInfo.scheduledEndTime)).getTime() - new Date((p.projectBaseInfo.scheduledStartTime)).getTime()
            fixerData[p.projectBaseInfo.projectName] = ((scheduledTime - actualTime) / scheduledTime * 100).toFixed(2);
        }
        let Data = [];
        for(let item in fixerData){
            Data.push({
                name:item,
                value:fixerData[item]
            })
        }
        Data.sort((item1,item2)=>{
            return item1.value - item2.value;
        })

        let chartDom_all = document.getElementById(domId);
        myChart_all = echarts.init(chartDom_all);
        let option_all = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            color: ['#2e80df', '#e9eaeb'],
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                top:"0",
                containLabel: true
            },
            xAxis: {
                type: 'value',
                boundaryGap: [0, 0.01]
            },
            yAxis: {
                type: 'category',
                data: Data.map((item,index)=>{
                    return item.name;
                })
            },
            series: [
                {
                    type: 'bar',
                    data: Data.map((item,index)=>{
                        return item.value;
                    }),
                    label:{
                        show:false
                    }
                },
            ]
        };
        myChart_all.setOption(option_all);
    })
}

window.addEventListener("resize", function () {
    myChart_all.resize();
});

export { drawOnTimeRankChart };