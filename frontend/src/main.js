import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import naive from 'naive-ui'
import axios from 'axios'
import * as echarts from 'echarts';
import 'echarts-liquidfill/dist/echarts-liquidfill.min.js'
import { useMessage } from 'naive-ui';

const message = useMessage();

// http request 拦截器
axios.interceptors.request.use(
    config => {
        if(router.currentRoute._value.fullPath!="/login"){
            let cache = JSON.parse(localStorage.getItem("nobug-system-cache"));
            if(cache == undefined){
                router.push("/login");
            } else {
                config.headers.token = cache.token;
            }
        }
        return config;
    },
    err => {
        return Promise.reject(err);
});

// http 响应 拦截器
axios.interceptors.response.use(
    res => {
        if(res.data.code && res.data.code == "50000"){
            router.push("/login");
        }
        return res;
    },
    err => {
        
        return Promise.reject(err);
});

let refresh = {func:null}
let reloadTimeCount = 0;
const app = createApp(App)
app.provide('$axios',axios);
app.provide('reload', () => {
    refresh.func();
    setTimeout(()=>{
        refresh.func();
    },100);
    if(reloadTimeCount == 0){
        setTimeout(()=>{
            refresh.func();
        },500);
        reloadTimeCount ++;
    }
})
app.use(naive)
app.use(store).use(router).mount('#app');

let BASE_URL_DEV = 'http://127.0.0.1:8081';
let BASE_URL_PRODUCTION = 'http://121.4.73.19:8081';

app.directive('has-role', {
    updated(el, binding, vnode){
        filterGlobalPermission(el, binding, vnode);
    },
    mounted(el, binding, vnode){
        filterGlobalPermission(el, binding, vnode);
    }
});

const filterGlobalPermission = (el, binding, vnode) => {
    let authList = JSON.parse(localStorage.getItem('nobug-system-cache')).permissions;
    let permissionFounded = false;
    for(let item of authList){
        if (item.permissionType == 0 && item.permissionName == binding.value) {
           permissionFounded = true;
        }
    }
    if(!permissionFounded) el.style.display = "None";
}

app.config.globalProperties.$baseURL = BASE_URL_PRODUCTION;
app.config.globalProperties.$baseImgURL = 'http://img.codesocean.top';
app.config.globalProperties.$refreshPage = refresh;

app.config.globalProperties.$echarts = echarts;