<template>
    <div id="main">
        <el-container>
            <el-aside width="auto">
                <menu-aside />
            </el-aside>
            <el-container>
                <el-header>
                    <header-bar-vue />
                </el-header>
                <el-main><router-view></router-view></el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import HeaderBarVue from "@/components/HeaderBar.vue";
    import MenuAside from "../components/MenuAside.vue";
    export default {
        name: "Main",
        components: { MenuAside, HeaderBarVue },
    };
</script>

<style lang="less" scoped>
    #main {
        .el-container {
            min-height: 100vh;
            .el-header {
                padding: 0;
            }
        }
        .el-aside {
            box-shadow: 0 0 5px 1px #140582;
        }
    }
</style>
