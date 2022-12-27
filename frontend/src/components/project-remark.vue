<template>
    <div class="outer">
        <n-form
            ref="formRef"
            label-placement="left"
            label-width="auto"
            require-mark-placement="right-hanging"
            size="large"
            :style="{
                width: '800px',
            }"
        >
            <n-form-item label="主管附加分" path="inputValue">
                <n-input-number
                    v-model:value="projectRemark.additionScore"
                    size="large"
                    placeholder="请输入附加分"
                    :disabled = "!projectRemark.editable"
                />
            </n-form-item>
            <n-form-item label="评价" path="inputValue">
                <n-input
                    v-model:value="projectRemark.message"
                    type="textarea"
                    placeholder="请输入评价"
                    :disabled = "!projectRemark.editable"
                />
            </n-form-item>
        </n-form>
        <div>
            <n-button type="primary" @click="reviewProject" v-show="projectRemark.editable"> 保存 </n-button>
        </div>
    </div>
</template>

<script>
import { inject, onMounted, getCurrentInstance } from "@vue/runtime-core";
import { h, ref, computed } from "vue";
import { useMessage } from "naive-ui";

export default {
    props: {
        projectId: Number,
        panelKey:String
    },
    setup(props, context) {
        const $axios = inject("$axios");
        const message = useMessage();
        const $baseURL =
            getCurrentInstance()?.appContext.config.globalProperties.$baseURL;

        let projectRemark = ref({
            additionScore: 0,
            message: "",
            editable:true
        });
        const tabManager = inject("FreeTabs");

        function reviewProject(){
            $axios.post($baseURL + '/projectScore/commitReviewProject',{
                additionScore:projectRemark.value.additionScore,
                message:projectRemark.value.message,
                reviewUserId:2,
                projectId:props.projectId
            }).then(res=>{
                if(res.data.respCode == 200){
                    tabManager.selfActive(props.panelKey).refreshPage();
                    message.success("保存成功")
                }
            }).catch(err=>{
                console.log(err);
                message.error("保存失败")
            })
        }

        onMounted(() => {
            $axios
                .get(
                    $baseURL +
                        "/project/getProjectDetailById?projectId=" +
                        props.projectId
                )
                .then((res) => {
                    // console.log(res.data);
                    if (res.data.respCode == 200) {
                        projectRemark.value.additionScore =
                            res.data.data.projectBaseInfo.additionScore;
                        projectRemark.value.message =
                            res.data.data.projectBaseInfo.message;
                        projectRemark.value.editable = (res.data.data.projectBaseInfo.status != 4)
                    }
                    
                });
        });
        return {
            projectRemark,reviewProject
        };
    },
};
</script>

<style scoped lang="scss">
.outer {
    padding: 20px;
    display: flex;
    flex-direction:column;
    align-items:center;
    min-height:calc(100vh - 300px);
}
</style>