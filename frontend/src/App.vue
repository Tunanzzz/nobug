<template>
    <n-config-provider :theme-overrides="appThemeOverrides">
        <n-dialog-provider>
            <n-message-provider>
                <n-loading-bar-provider>
                    <router-view></router-view>
                </n-loading-bar-provider>
            </n-message-provider>
        </n-dialog-provider>
    </n-config-provider>
</template>

<script>
import { NConfigProvider } from "naive-ui";
import appThemeOverrides from "./themes/app.json";
import * as echarts from 'echarts'
import { provide } from 'vue'
export default {
    components: {
        NConfigProvider,
    },
    setup() {
        provide('ec',echarts)//provide
        return {
            appThemeOverrides,
        };
    },
};
</script>

<style lang="scss">
@font-face
{
    font-family: "阿里巴巴普惠体";
    src: url('./static/阿里巴巴普惠体.ttf'),
}
@font-face
{
    font-family: "阿里巴巴普惠体";
    font-weight:bold;
    src: url('./static/阿里巴巴普惠体 B.ttf'),
}
*{
    font-family: "阿里巴巴普惠体";
}
.n-tabs-tab--active {
    background-color: #fff !important;
}
.n-scrollbar-rail{
    z-index:10000;
}
</style>
