import Vue from "vue";

import App from "./App.vue";
//引入elementui
import ElementUI from "element-ui";
//引入elementui样式
import "element-ui/lib/theme-chalk/index.css";
//引入路由
import VueRouter from "vue-router";
//引入vuex仓库
import store from "./store";
//引入路由组件
import router from "./router";

//应用这些玩意儿
Vue.use(VueRouter);
Vue.use(ElementUI);
Vue.config.productionTip = false;

new Vue({
    render: h => h(App),
    //挂载vuex
    store,
    //挂载路由
    router,
    //安装全局事件总线
    beforeCreate() {
        Vue.prototype.$bus = this;
    },
}).$mount("#app");
