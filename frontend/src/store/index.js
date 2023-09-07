//引入vuex
import Vuex from "vuex";
//引入vue
import Vue from "vue";
import tab from "./tab";
import userInfo from "./userInfo";

Vue.use(Vuex);

export default new Vuex.Store({
    modules: { tab, userInfo },
});
