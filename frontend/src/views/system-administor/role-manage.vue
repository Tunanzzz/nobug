<template>
    <div class="outer">
        <n-button type="info" style="margin-bottom:20px;" @click="showNewroleModal = true"> 添加角色 </n-button>
        <n-spin :show="roleSpinning">
            <n-space vertical class="roleList">
                <n-table striped>
                <thead>
                    <tr>
                        <th>角色ID</th>
                        <th>角色名</th>
                        <th>角色权限</th>
                        <th>角色状态</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="role in roleList" :key="role.role_id">
                        <td>{{role.roleId}}</td>
                        <td >
                                {{role.roleName}}
                        </td>
                        <td width="500">
                            <n-tag :type="permission.permissionType == 1 ? 'info' : 'success'" v-for="permission in role.permissions" :key="permission.permissionId" style="margin-left:8px;margin-bottom:8px"
                            >
                                {{permission.permissionDescription}}
                            </n-tag>
                            
                        </td>
                        <td>{{role.isDelete == 0 ? '可用' : '禁用'}}</td>
                        <td style="width:200px">
                            <n-button type="info" @click="startEditrole(role)" > 编辑 </n-button>
                            <n-popconfirm
                                @positive-click="removerole(role.roleId)"
                                positive-text="确认"
                                negative-text="取消"
                            >
                                <template #trigger>
                                    <n-button type="error" style="margin-left:10px" v-show="role.isDelete == 0"> 禁用 </n-button>
                                </template>
                                    确认禁用？
                            </n-popconfirm>
                            <n-popconfirm
                                @positive-click="removerole(role.roleId)"
                                positive-text="确认"
                                negative-text="取消"
                            >
                                <template #trigger>
                                    <n-button type="success" style="margin-left:10px" v-show="role.isDelete == 1"> 启用 </n-button>
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

    <n-modal v-model:show="showNewroleModal" preset="card" title="添加用户" style="width:500px">
        <template #header>
            <div>添加角色</div>
        </template>
            <n-form
                :model="newroleModelRef"
                label-placement="left"
                label-width="auto"
                require-mark-placement="right-hanging"
            >
                <n-form-item path="role_name" label="角色名">
                    <n-input v-model:value="newroleModelRef.roleName" @keydown.enter.prevent placeholder="请输入密码" />
                    </n-form-item>
            </n-form>
        <template #action>
            <n-button @click="showNewroleModal = false"> 取消 </n-button>
            <n-button type="info" style="margin-left:10px" @click="addrole"> 添加 </n-button>
        </template>
    </n-modal>
    <n-modal v-model:show="showEditroleModal" preset="card" title="编辑用户" style="width:500px">
        <template #header>
            <div>编辑角色</div>
        </template>
            <n-form
                label-placement="left"
                label-width="auto"
                require-mark-placement="right-hanging"
            >
                <n-form-item path="roleName" label="角色名">
                    <n-input v-model:value="editroleModelRef.roleName" @keydown.enter.prevent />
                    </n-form-item>
                <n-form-item path="permissionList" label="权限">
                    <n-select v-model:value="editroleModelRef.permissions" multiple :options="permissionList" />
                </n-form-item>
            </n-form>
        <template #action>
            <n-button @click="showEditroleModal = false"> 取消 </n-button>
            <n-button type="info" style="margin-left:10px" @click="editrole"> 提交 </n-button>
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
        const roleList = ref([]);
        const permissionList = reactive([]);
        const reload = inject('reload')
        const roleSpinning = ref(false);
        
        let pageCount = ref(0);
        let curPage = ref(1);

        onMounted(()=>{
            nextPage(1);
            getAllPermission();
        });

        function nextPage(page){
            curPage.value = page;
            roleSpinning.value = true;
            $axios.get($baseURL + '/role/getRoleListByPage?pageNum=' + page + '&pageSize=8').then(res=>{
                if(res.data.respCode == 200){
                    pageCount.value = Math.ceil(res.data.data.totalCount / 8);
                    roleList.value = [];
                    for(let item of res.data.data.roleList){
                        roleList.value.push(item)
                        console.log(item)
                    }
                } else {
                    message.error("获取角色列表失败")
                }
            }).catch(err=>{
                console.log(err);
                message.error('请求失败');
            }).then(()=>{
                roleSpinning.value = false;
            })
        }

        function getAllPermission(){
            $axios.get($baseURL + '/permission/getAllPermission')
            .then(res=>{
                if(res.data.respCode == 200){
                    console.log(res.data.data)
                    for(let permission of res.data.data){
                        permissionList.push({
                            label:permission.permissionDescription,
                            value:permission.permissionId,
                            disabled:(permission.isDelete == 1)
                        });
                    }
                }
            }).catch(err=>{
                message.error("获取权限列表失败")
            })
        }

        function removerole(role_id){
            // console.log(role_id)
            $axios.post($baseURL + '/role/deleteRoleById', {
                roleId: role_id
            })
            .then(function (response) {
                console.log(response);
                message.success('删除/恢复成功');
                reload();
            })
            .catch(function (error) {
                console.log(error);
                if(error) {
                    message.error('删除/恢复失败');
                }
            });
        }

        function addrole(){
            $axios.post($baseURL + '/role/addRole', {
                roleName: newroleModelRef.value.roleName,
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

        function editrole(){
            //将roles送进指定数据格式中
            let permissions = [];
            for(let role of editroleModelRef.value.permissions){
                permissions.push(role)
            }
            console.log(permissions);
            $axios.post($baseURL + '/role/updateRoleByIdByArray', {
                roleName: editroleModelRef.value.roleName,
                permissionIdList: permissions,
                roleId:editroleModelRef.value.roleId
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

        let showNewroleModal = ref(false)
        const newroleModelRef = ref({
            roleName: null,
            rolePassword: null
        });


        let showEditroleModal = ref(false)
        const editroleModelRef = ref({
            roleName: null,
            permissions: null,
            roleId:null
        });

        function startEditrole(role){
            editroleModelRef.value.roleId = role.roleId;
            editroleModelRef.value.roleName = role.roleName;
            editroleModelRef.value.permissions = [];
            for(let item of role.permissions){
                editroleModelRef.value.permissions.push(item.permissionId);
            }
            showEditroleModal.value = true;
            console.log(showEditroleModal)
            console.log(editroleModelRef.value);
        }

        return{
            roleList,showNewroleModal,newroleModelRef,addrole,removerole,showEditroleModal,curPage,pageCount,roleSpinning,
            editroleModelRef,startEditrole,editrole,nextPage,permissionList
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