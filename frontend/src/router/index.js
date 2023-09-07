import VueRouter from "vue-router";
import store from "@/store";

//登录首页
import Login from "../views/Login";

//根路由
import Main from "../views/Main";
//首页路由
import Home from "../views/Home";
//用户路由
import User from "../views/User";
//角色路由
import Role from "../views/Role";
//引入Cookie组件
import Cookies from "js-cookie";

const router = new VueRouter({
    routes: [
        //后台
        {
            path: "/",
            component: Main,
            meta: { isAuth: true, roleKey: ["ROLE_COMMON", "ROLE_ADMIN"] },
            children: [
                {
                    path: "home",
                    name: "home",
                    meta: {
                        isAuth: true,
                        roleKey: ["ROLE_COMMON", "ROLE_ADMIN"],
                    },
                    component: Home,
                },
                {
                    path: "user",
                    meta: {
                        isAuth: true,
                        roleKey: ["ROLE_ADMIN"],
                    },
                    component: User,
                },
                {
                    path: "role",
                    meta: {
                        isAuth: true,
                        roleKey: ["ROLE_ADMIN"],
                    },
                    component: Role,
                },
            ],
        },
        //登录
        {
            path: "/login",
            name: "login",
            meta: { isAuth: false, roleKey: ["ROLE_COMMON", "ROLE_ADMIN"] },
            component: Login,
        },
    ],
});

//全局前置守卫
router.beforeEach((to, from, next) => {
    //获取token
    const token = Cookies.get("token");

    //获取用户角色
    const roleKey = store.state.userInfo.userRole.roleKey;
    //获取要去的也页面meta中的roleKey组合
    const roleKeyArr = to.meta.roleKey;

    //去需要判断的页面
    if (to.meta.isAuth) {
        //如果有token则继续
        if (token) {
            if (!roleKeyArr.includes(roleKey)) {
                //如果无权限则返回home页面
                alert("您没权限");
            } else {
                next();
            }
        } else {
            //没有token则去登陆页面
            next({ name: "login" });
        }
    } else {
        //去login页面
        //如果有token
        if (token) {
            next({ name: "home" });
        } else {
            //没有则没有
            next();
        }
    }
});

export default router;
