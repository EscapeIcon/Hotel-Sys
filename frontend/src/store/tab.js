export default {
    namespaced: true,
    actions: {},
    mutations: {
        changeCollapseState(state) {
            state.isCollapse = !state.isCollapse;
        },
    },
    state: {
        isCollapse: false,
    },
};
