import * as echarts from 'echarts';
let myChart_all = undefined;
function getMaxThreshold(data,parameterName){
    let maxThreshold = 0;
    for(let item of data){
        if(item.parameterName == parameterName){
            maxThreshold = Math.max(item.parameterScore,maxThreshold);
        }
    }
    return maxThreshold;
}
function drawTotalScoreChart(data,domId){
    setTimeout(()=>{
        let chartDom_all = document.getElementById(domId);
        myChart_all = echarts.init(chartDom_all);
        let indicators = [
            { name: '缺陷', max: 100, color:'black'},
            { name: '时间', max: 100, color:'black'},
            { name: '人力', max: 100, color:'black'}
        ];
        for(let item of data.schemeInfo.assessParameterList){
            if(item.parameterName == "项目完成及时率") indicators[1].max = item.parameterWeight * 100 * getMaxThreshold(data.schemeInfo.thresholdVoList,"项目完成及时率");
            if(item.parameterName == "人员成本") indicators[2].max = item.parameterWeight * 100 * getMaxThreshold(data.schemeInfo.thresholdVoList,"人员成本");
            if(item.parameterName == "代码质量") indicators[0].max = item.parameterWeight * 100 * getMaxThreshold(data.schemeInfo.thresholdVoList,"代码质量");
        }
        let option_all = {
            tooltip: {
                trigger: 'item'
            },
            radar: {
                // shape: 'circle',
                indicator: indicators,
                startAngle: 110
            },
            series: [
                {
                    name: '考核总分',
                    type: 'radar',
                    data: [
                        {
                            value: [
                                data.projectBaseInfo.defectScore,
                                data.projectBaseInfo.onTimeScore,
                                data.projectBaseInfo.costTimeScore
                            ]
                        }
                    ],
                    areaStyle: {
                        color: 'rgba(4, 103, 215, .6)'
                    },
                    color:"#0467d7"
                }
            ]
        };
        myChart_all.setOption(option_all);
    })
}

window.addEventListener("resize", function() {                
    myChart_all.resize();
});

export {drawTotalScoreChart};