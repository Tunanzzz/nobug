import * as echarts from 'echarts';
import moment from 'moment';
let myChart_all = undefined;
function drawOnTimeLiquidFillOverLookChart(data,domId){
    setTimeout(()=>{
        let actualTimeSum = 0;
        let scheduledTimeSum = 0;
        for(let item of data){
            actualTimeSum = actualTimeSum + new Date((item.projectBaseInfo.actualEndTime)).getTime()  - new Date((item.projectBaseInfo.actualStartTime)).getTime() 
            scheduledTimeSum = scheduledTimeSum + new Date((item.projectBaseInfo.scheduledEndTime)).getTime()  - new Date((item.projectBaseInfo.scheduledStartTime)).getTime()
        }
        let des = (actualTimeSum < scheduledTimeSum) ? '节省' : "超量";
        let sta;
        if(des == '节省'){
            sta = ((scheduledTimeSum - actualTimeSum) / scheduledTimeSum * 100).toFixed(0);
        } else {
            sta = ((actualTimeSum - scheduledTimeSum) / scheduledTimeSum * 100).toFixed(0);
        }
        let color = des == '节省' ? '#2e80df' : '#d15664';
        

        let chartDom_all = document.getElementById(domId);
        myChart_all = echarts.init(chartDom_all);
        let option_all = {
            tooltip: {
                trigger: 'item',
                formatter:function(params){
                    let str = "计划总时间："
                    str += (scheduledTimeSum / 3600 / 60 / 24).toFixed(2);
                    str += '天'
                    str += "<br/>实际总时间：";
                    str +=(actualTimeSum / 3600 / 60 / 24).toFixed(2);
                    str += '天'
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

export {drawOnTimeLiquidFillOverLookChart};