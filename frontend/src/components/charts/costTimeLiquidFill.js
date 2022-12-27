import * as echarts from 'echarts';
let myChart_all = undefined;
function drawCostTimeLiquidFillChart(data,domId){
    setTimeout(()=>{
        let des = (data.projectBaseInfo.timeAll < data.projectBaseInfo.timeInDue) ? '节省' : "超量";
        let sta;
        if(des == '节省'){
            sta = ((data.projectBaseInfo.timeInDue - data.projectBaseInfo.timeAll) / data.projectBaseInfo.timeInDue * 100).toFixed(0);
        } else {
            sta = ((data.projectBaseInfo.timeAll - data.projectBaseInfo.timeInDue) / data.projectBaseInfo.timeInDue * 100).toFixed(0);
        }
        let color = des == '节省' ? '#2e80df' : '#d15664';

        let chartDom_all = document.getElementById(domId);
        myChart_all = echarts.init(chartDom_all);
        let option_all = {
            tooltip: {
                trigger: 'item',
                formatter:function(params){
                    let str = "计划总人时："
                    str += data.projectBaseInfo.timeInDue.toFixed(2);
                    str += "<br/>实际总人时：";
                    str += data.projectBaseInfo.timeAll.toFixed(2);
                    return str;
                },
            },
            series:[
              {
                type:'liquidFill',
                outline: {
                  show: false
                },
                backgroundStyle: {
                  color: 'rgba(255, 255, 255, 0)'
                },
                shape: 'circle',
                radius: '100%',
                itemStyle: {
                  color: color,
                  shadowBlur: 0,
                  opacity: .7
                },
                data:[sta /100],
                label:{
                    show:true,
                    formatter:function(params){
                        return des + sta + "%";
                    },
                    textStyle:{
                        fontSize:'40px',
                        color:color
                    }
                },
                animationEasing: 'quarticInOut',
                amplitude:6,
                emphasis:{
                    itemStyle: {
                        opacity :1 //鼠标经过波浪颜色的透明度
                    }
                },
              }
            ]
          }
        myChart_all.setOption(option_all);
    })
}

window.addEventListener("resize", function() {                
    myChart_all.resize();
});

export {drawCostTimeLiquidFillChart};