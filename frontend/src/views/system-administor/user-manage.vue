<template>
    <div class="outer">
        <n-button type="info" style="margin-bottom:20px;" @click="showNewUserModal = true"> 添加用户 </n-button>
        <n-spin :show="userSpinning">
            <n-space vertical class="userList">
                <n-table striped>
                <thead>
                    <tr>
                        <th>用户ID</th>
                        <th>用户名</th>
                        <th>用户角色</th>
                        <th>用户状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="user in userList" :key="user.user_id">
                        <td>{{user.userId}}</td>
                        <td style="display:flex; vertical-align: center;">
                            <n-avatar
                                size="small"
                                :src="user.userPortrait"
                                style="margin:1em 0;"
                                />
                            <p style="margin-left:10px; height:28px;transform:translateY(3px);">
                                {{user.userName}}
                            </p>
                        </td>
                        <td>
                            <n-tag type="info" v-for="role in user.roles" :key="role.roleId" style="margin-left:8px;">
                                {{role.roleName}}
                            </n-tag>
                            
                        </td>
                        <td>{{(user.isDelete == 0)?"启用中":"禁用中"}}</td>
                        <td style="width:200px">
                            <n-button type="info" @click="startEditUser(user)" v-show="user.userName != 'admin'"> 编辑 </n-button>
                            <n-popconfirm
                                @positive-click="removeUser(user.userId)"
                                positive-text="确认"
                                negative-text="取消"
                            >
                                <template #trigger>
                                    <n-button type="error" style="margin-left:10px" v-show="user.userName != 'admin' && user.isDelete == 0"> 禁用 </n-button>
                                </template>
                                    确认禁用？
                            </n-popconfirm>
                            <n-popconfirm
                                @positive-click="removeUser(user.userId)"
                                positive-text="确认"
                                negative-text="取消"
                            >
                                <template #trigger>
                                    <n-button type="success" style="margin-left:10px" v-show="user.userName != 'admin' && user.isDelete == 1"> 启用 </n-button>
                                </template>
                                    确认启用？
                            </n-popconfirm>
                        </td>
                    </tr>
                </tbody>
                </n-table>
            </n-space>
        </n-spin>
        <n-pagination v-model:page="curPage" :page-count="pageCount" style="float:right;margin:20px 0;" :on-update:page="nextPage" />
        <div class="bottom" style="height:50px;width:100%;"></div>
    </div>

    <n-modal v-model:show="showNewUserModal" preset="card" title="添加用户" style="width:500px">
        <template #header>
            <div>添加用户</div>
        </template>
            <n-form
                :model="newUserModelRef"
                label-placement="left"
                label-width="auto"
                require-mark-placement="right-hanging"
            >
                <n-form-item path="user_name" label="用户名">
                    <n-input v-model:value="newUserModelRef.userName" @keydown.enter.prevent placeholder="请输入用户名"/>
                    </n-form-item>
                <n-form-item path="user_password" label="密码">
                    <n-input
                        v-model:value="newUserModelRef.userPassword"
                        type="password"
                        @keydown.enter.prevent
                        placeholder="请输入密码"
                    />
                </n-form-item>
            </n-form>
        <template #action>
            <n-button @click="showNewUserModal = false"> 取消 </n-button>
            <n-button type="info" style="margin-left:10px" @click="addUser"> 添加 </n-button>
        </template>
    </n-modal>
    <n-modal v-model:show="showEditUserModal" preset="card" title="编辑用户" style="width:500px">
        <template #header>
            <div>编辑用户</div>
        </template>
            <n-form
                :model="editUserModelRef"
                label-placement="left"
                label-width="auto"
                require-mark-placement="right-hanging"
            >
                <n-form-item path="userName" label="用户名">
                    <n-input v-model:value="editUserModelRef.userName" placeholder="请输入用户名" @keydown.enter.prevent />
                    </n-form-item>
                <n-form-item path="roleName" label="角色">
                    <n-select v-model:value="editUserModelRef.roles" multiple placeholder="请选择角色" :options="rolesList" />
                </n-form-item>
            </n-form>
        <template #action>
            <n-button @click="showEditUserModal = false"> 取消 </n-button>
            <n-button type="info" style="margin-left:10px" @click="editUser"> 提交 </n-button>
        </template>
    </n-modal>

</template>

<script>
import { h, reactive, ref, computed } from "vue";
import { getCurrentInstance, onMounted,inject} from '@vue/runtime-core'
import { useMessage } from 'naive-ui'
export default {
    props:{
        panelKey:String
    },
    setup(){
        const $axios = inject('$axios');
        const message = useMessage()
        const $baseURL = getCurrentInstance()?.appContext.config.globalProperties.$baseURL;
        const userList = ref([]);
        const reload = inject('reload')
        const rolesList = reactive([]);
        const userSpinning = ref(false);

        let pageCount = ref(0);
        let curPage = ref(1);

        onMounted(()=>{
            nextPage(1);
            $axios.get($baseURL + '/role/getAllRoles').then(res=>{
                for(let item of res.data.data.slide_list){
                    rolesList.push({
                        label:item.roleName,
                        value:item.roleId,
                        disabled:(item.isDelete == 1)
                    })
                }
            }).catch(err=>{
                message.error('获取失败');
            })
        });

        function nextPage(page){
            curPage.value = page;
            userSpinning.value = true;
            $axios.get($baseURL + '/user/getUserListByPage?pageNum=' + page + '&pageSize=8').then(res=>{
                if(res.data.respCode == 200){
                    pageCount.value = Math.ceil(res.data.data.totalCount / 8);
                    userList.value = [];
                    for(let item of res.data.data.userList){
                        userList.value.push(item)
                    }
                } else {
                    message.error("获取用户列表失败")
                }
            }).catch(err=>{
                console.log(err);
                message.error('请求失败');
            }).then(()=>{
                userSpinning.value = false;
            })
        }

        function removeUser(user_id){
            console.log(user_id)
            $axios.post($baseURL + '/user/removeUserById', {
                userId: user_id
            })
            .then(function (response) {
                console.log(response);
                message.success('删除成功');
                reload();
            })
            .catch(function (error) {
                console.log(error);
                if(error) {
                    message.error('删除失败');
                }
            });
        }

        function addUser(){
            $axios.post($baseURL + '/user/register', {
                userName: newUserModelRef.value.userName,
                userPassword: newUserModelRef.value.userPassword,
            })
            .then(function (response) {
                message.success('成功');
                reload();
            })
            .catch(function (error) {
                console.log(error);
                if(error) {
                    message.error('添加失败');
                }
            });
        }

        function editUser(){
            //将roles送进指定数据格式中
            let roles = [];
            for(let role of editUserModelRef.value.roles){
                roles.push({
                    roleId:role
                })
            }
            console.log(roles);
            $axios.post($baseURL + '/user/updateUserById', {
                userName: editUserModelRef.value.userName,
                roles: roles,
                userId:editUserModelRef.value.userId
            })
            .then(function (response) {
                message.success('成功');
                reload();
            })
            .catch(function (error) {
                console.log(error);
                if(error) {
                    message.error('修改失败');
                }
            });
        }

        let showNewUserModal = ref(false)
        const newUserModelRef = ref({
            userName: null,
            userPassword: null
        });

        let showEditUserModal = ref(false)
        const editUserModelRef = ref({
            userName: null,
            roles: null,
            userId:null
        });

        function startEditUser(user){
            editUserModelRef.value.userName = user.userName;
            editUserModelRef.value.roles = [];
            for(let item of user.roles){
                editUserModelRef.value.roles.push(item.roleId);
            }
            editUserModelRef.value.userId = user.userId;
            showEditUserModal.value = true;
            console.log(editUserModelRef.value);
        }

        return{
            userList,showNewUserModal,newUserModelRef,rolesList,addUser,removeUser,showEditUserModal,curPage,pageCount,userSpinning,
            editUserModelRef,startEditUser,editUser,nextPage
        }
    }
}
</script>

<style scoped>
    .outer{
        background-color:white;
        padding:20px;
    }
</style>