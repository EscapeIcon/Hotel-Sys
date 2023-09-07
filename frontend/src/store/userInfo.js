export default {
    namespaced: true,
    actions: {},
    mutations: {
        //用户登录添加用户信息mutations方法
        ADDUSERINFO(state, val) {
            //添加菜单信息
            state.menuInfo = val.menus;
            delete val.menus;
            //添加用户角色
            state.userRole = val.role;
            delete val.role;
            //添加用户信息
            state.userData = val;
        },
        //注销登录
        REMOVEUSERINFO(state, val) {
            //删除所有当前用户信息
            state.menuInfo = [];
            state.userData = {};
            state.userRole = {};
        },
    },
    state: {
        //菜单信息
        menuInfo: [
            {
                id: 1,
                name: "首页",
                path: "/home",
                icon: "el-icon-house",
                description: "首页",
                pid: null,
                children: [],
            },
            {
                id: 2,
                name: "系统管理",
                path: "",
                icon: "el-icon-setting",
                description: "系统管理",
                pid: null,
                children: [
                    {
                        id: 3,
                        name: "用户管理",
                        path: "/user",
                        icon: "el-icon-user",
                        description: "用户管理",
                        pid: 2,
                        children: null,
                    },
                    {
                        id: 6,
                        name: "角色管理",
                        path: "/role",
                        icon: "el-icon-s-custom",
                        description: "角色管理",
                        pid: 2,
                        children: null,
                    },
                    {
                        id: 7,
                        name: "菜单管理",
                        path: "/menu",
                        icon: "el-icon-menu",
                        description: "菜单管理",
                        pid: 2,
                        children: null,
                    },
                ],
            },
        ],
        //用户信息
        userData: {
            avatarUrl:
                "https://ianlit-picture-beijing.oss-cn-beijing.aliyuncs.com/img/admin.jpg",
            createTime: "2000-5-15 12:30:30",
            id: 1,
            nickname: "随便",
            phone: "12311112222",
            status: 1,
            username: "admin",
        },
        //用户角色
        userRole: {
            id: 0,
            name: "超级管理员",
            roleKey: "ROLE_ADMIN",
        },
    },
};
