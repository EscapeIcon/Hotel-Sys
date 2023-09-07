<template>
    <el-menu
        class="el-menu-vertical-demo"
        :collapse="isCollapse"
        background-color="#E99667"
        text-color="#fff"
        :router="true"
        active-text-color="#E8416A"
        :default-active="$route.path"
    >
        <!-- 标题 -->
        <div id="title">
            <div class="img">
                <img src="../assets/logo.jpg" />
            </div>
            <h3>&nbsp;后台权限管理系统</h3>
        </div>

        <!-- 无子项标签 -->
        <el-menu-item
            :index="item.path"
            v-for="item in getNoChild"
            :key="item.id"
        >
            <i :class="item.icon"></i>
            <span slot="title">{{ item.name }}</span>
        </el-menu-item>

        <!-- 有子项标签 -->
        <el-submenu
            :index="item.name"
            v-for="item in getHaveChild"
            :key="item.index"
        >
            <template slot="title">
                <i :class="item.icon"></i>
                <span slot="title">{{ item.name }}</span>
            </template>
            <el-menu-item
                :index="itemChild.path"
                v-for="itemChild in item.children"
                :key="itemChild.id"
            >
                <i :class="itemChild.icon"></i>
                <span slot="title">{{ itemChild.name }}</span>
            </el-menu-item>
        </el-submenu>
    </el-menu>
</template>

<script>
    import { mapState } from "vuex";
    export default {
        name: "MenuAside",
        data() {
            return {
                asideData: [],
                userMenu: [],
            };
        },
        computed: {
            //获取有子菜单的菜单项
            getNoChild() {
                return this.userMenu?.filter(item => !item.children.length);
            },
            //获取无子菜单的菜单项
            getHaveChild() {
                return this.userMenu?.filter(item => item.children.length);
            },
            //获取state中的是否收缩侧边栏状态数据
            ...mapState("tab", ["isCollapse"]),
        },
        mounted() {
            this.userMenu = this.$store.state.userInfo.menuInfo;
        },
    };
</script>

<style lang="less" scoped>
    //自带样式
    .el-menu-vertical-demo:not(.el-menu--collapse) {
        width: 250px;
        min-height: 100vh;
    }
    //侧边栏整体样式
    .el-menu {
        height: 100%;
        border: none;

        //标题样式
        #title {
            height: 70px;
            padding: 0 10px 0 10px;
            display: flex;
            justify-content: space-evenly;
            align-items: center;
            //logo盒子样式
            .img {
                min-width: 50px;
                width: 50px;
                border-radius: 100%;
                overflow: hidden;
                //logo图片样式
                img {
                    width: 100%;
                    vertical-align: top;
                }
            }
            //标题字体样式
            h3 {
                // margin-left: 10px;
                overflow: hidden;
                white-space: nowrap;
                text-align: center;
                color: #fff;
            }
        }
    }
</style>
