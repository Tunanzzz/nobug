import dashBoard from './views/dashboard.vue';
import examineList from './views/project-manage/examine-list.vue';
import userManage from './views/system-administor/user-manage.vue';
import projectOverlook from './views/project-manage/project-overlook.vue';
import examineParameter from './views/system-administor/examine-scheme.vue';
import examineDetail from './views/project-manage/examine-detail.vue';
import roleManage from './views/system-administor/role-manage.vue';

const views = {
    dashBoard,userManage,examineList,examineParameter,projectOverlook,roleManage,examineDetail
}


const tabsTranslation = {
    "dashBoard":"主控台",
    "examineList":"项目考核 - 列表",
    "userManage":"用户管理",
    "projectOverlook":"项目考核 - 总览",
    "examineParameter":"考核方案管理",
    "roleManage":"角色管理",
    "examineDetail":"考核详情"
}
export {views,tabsTranslation};