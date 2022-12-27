import * as echarts from 'echarts';
import moment from 'moment';
let myChart_all = undefined;
function drawOnTimeLiquidFillChart(data,domId){
    setTimeout(()=>{
        let des = (data.projectBaseInfo.actualTime < data.projectBaseInfo.scheduledTime) ? '节省' : "超量";
        let sta;
        if(des == '节省'){
            sta = ((data.projectBaseInfo.scheduledTime - data.projectBaseInfo.actualTime) / data.projectBaseInfo.scheduledTime * 100).toFixed(0);
        } else {
            sta = ((data.projectBaseInfo.actualTime - data.projectBaseInfo.scheduledTime) / data.projectBaseInfo.scheduledTime * 100).toFixed(0);
        }
        let color = des == '节省' ? '#2e80df' : '#d15664';
        

        let chartDom_all = document.getElementById(domId);
        myChart_all = echarts.init(chartDom_all);
        let option_all = {
            tooltip: {
                trigger: 'item',
                formatter:function(params){
                    let str = "计划时间："
                    str += moment(data.projectBaseInfo.scheduledStartTime).format("YYYY年MM月DD日");
                    str += "-";
                    str += moment(data.projectBaseInfo.scheduledEndTime).format("YYYY年MM月DD日");
                    str += "<br/>实际时间：";
                    str += moment(data.projectBaseInfo.actualStartTime).format("YYYY年MM月DD日");
                    str += "-";
                    str += moment(data.projectBaseInfo.actualEndTime).format("YYYY年MM月DD日");
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
                data:[sta / 100],
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

export {drawOnTimeLiquidFillChart};