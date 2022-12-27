<template>
    <div class="outer">
        <background class="bg"></background>
        <div class="login" :class="{'active':activeMenu == 'login'}">
            <div class="logo">
                <img src="./assets/logo.png" alt="">
            </div>
            <div class="title">
                登录
            </div>
            <div class="form">
                <n-input v-model:value="loginData.username" size="large" type="text" placeholder="用户名" />
                <n-input v-model:value="loginData.pwd" size="large" type="password" placeholder="密码" />
            </div>
            <div class="newAccount">
                没有账号？
                <n-button text color="#0e4f9b" size="custom" @click="activeMenu = 'register'">
                    创建一个！
                </n-button>
            </div>
            <div class="btn">
                <n-spin :show="loginSpinning" size="small">
                    <n-button type="primary" size="large" @click="login">
                        登录
                    </n-button>
                </n-spin>
            </div>
        </div>
        <div class="register" :class="{'active':activeMenu == 'register'}">
            <div class="logo">
                <img src="./assets/logo.png" alt="">
            </div>
            <div class="title">
                申请账号
            </div>
            <div class="form">
                <n-input v-model:value="loginData.username" size="large" type="text" placeholder="用户名" />
                <n-input v-model:value="loginData.pwd" size="large" type="password" placeholder="密码" />
            </div>
            <div class="newAccount">
                已有账号？
                <n-button text color="#0e4f9b" size="custom" @click="activeMenu = 'login'">
                    去登录！
                </n-button>
            </div>
            <div class="btn">
                <n-button type="primary" size="large">
                    申请
                </n-button>
            </div>
        </div>
    </div>
</template>

<script>
import { getCurrentInstance, onMounted,reactive,inject} from '@vue/runtime-core'
import { h, ref, computed } from "vue";
import { useMessage } from 'naive-ui'
import background from "./components/background.vue"
import {useRouter} from 'vue-router';
export default {
    components:{
        background
    },
    setup(){
        let activeMenu = ref("login");
        const $axios = inject('$axios');
        const message = useMessage()
        const router = useRouter();

        let loginSpinning = ref(false);

        let loginData = ref({
            username:"",
            pwd:""
        });

        let registerData = ref({
            username:"",
            pwd:""
        });

        const $baseURL = getCurrentInstance()?.appContext.config.globalProperties.$baseURL;

        function login(){
            loginSpinning.value = true;
            $axios.post($baseURL + '/user/login', {
                userName:loginData.value.username,
                userPassword:loginData.value.pwd
            })
            .then(function (response) {
                // console.log(response)
                if(response.data.respCode != "200"){
                    message.error('用户名或密码错误');
                } else {
                    console.log(response.data);
                    window.localStorage.setItem('nobug-system-cache', JSON.stringify(response.data.data));
                    router.push('/');
                    message.success('登录成功');
                }
            })
            .catch(function (error) {
                if(error) {
                    message.error('请求错误');
                }
            }).then(function (error) {
                loginSpinning.value = false;
            });
        }

        return{
            loginSpinning,
            activeMenu,loginData,registerData,
            login
        }
    }
}
</script>

<style scoped lang="scss">
.outer{
    width:100vw;
    height:100vh;
    overflow:hidden;
    user-select:none;
    position:absolute;
    .bg{
        position:absolute
    }
    .login,.register{
        height:340px;
        width:350px;
        position:absolute;
        left:50vw;
        top:50vh;
        transform:translate(-50%,-50%);
        padding:0;
        background-color:#ffffff88;
        box-shadow:
            0px 1.1px 2.2px rgba(0, 0, 0, 0.006),
            0px 2.7px 5.3px rgba(0, 0, 0, 0.008),
            0px 5px 10px rgba(0, 0, 0, 0.01),
            0px 8.9px 17.9px rgba(0, 0, 0, 0.012),
            0px 16.7px 33.4px rgba(0, 0, 0, 0.014),
            0px 40px 80px rgba(0, 0, 0, 0.02);
        border-radius:5px;
        backdrop-filter: blur(5px);
        padding:45px;
        .logo{
            img{
                height:50px;
            }
        }
        .title{
            margin-left:8px;
            margin-top:15px;
            font-size:32px;
            font-weight:bold;

        }
        .form{
            margin:8px;
            .n-input:nth-child(1){
                margin-top:10px;
            }
            .n-input:nth-child(2){
                margin-top:15px;
            }
        }
        .newAccount{
            margin-left:8px;
            margin-top:24px;
            font-size:16px;
        }
        .btn{
            margin:12px 8px;
            justify-content: flex-end;
            display:flex;
        }
    }
    .login{
        opacity:0;
        left:-25vw;
        transition:all .8s;
    }
    .register{
        opacity:0;
        left:125vw;
        transition:all .8s;
    }
    .login.active{
        opacity:1;
        left:50vw;
    }
    .register.active{
        opacity:1;
        left:50vw;
    }
}


</style>