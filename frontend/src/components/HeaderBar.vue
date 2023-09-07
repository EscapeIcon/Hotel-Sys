<template>
    <div id="headerArea">
        <!-- 左侧显示区 -->
        <div class="leftArea">
            <!-- 收缩侧边栏按钮 -->
            <el-button
                size="small"
                type="primary"
                :icon="
                    !isCollapse
                        ? 'el-icon-d-arrow-left'
                        : 'el-icon-d-arrow-right'
                "
                @click="changeCollapseState"
            ></el-button>
            <span class="text">首页</span>
        </div>
        <!-- 右侧显示区 -->
        <div class="rightArea">
            <el-dropdown @command="exitUser">
                <span class="el-dropdown-link">
                    <div class="iconImg">
                        <img :src="userData.avatarUrl" />
                    </div>
                    <h3>{{ userData.username }}</h3>
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item command="userInfo"
                        >个人信息</el-dropdown-item
                    >
                    <el-dropdown-item command="exitUser"
                        >退出登录</el-dropdown-item
                    >
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </div>
</template>

<script>
    //引入map的mutations和state的映射方法
    import { mapMutations, mapState } from "vuex";
    //引入cookie组件
    import Cookie from "js-cookie";
    export default {
        name: "HeaderBar",
        computed: {
            //map映射vuex中tab的判断是否收缩侧边栏flag
            ...mapState("tab", ["isCollapse"]),
            ...mapState("userInfo", ["userData"]),
        },
        methods: {
            //改变侧边栏状态事件
            ...mapMutations("tab", ["changeCollapseState"]),
            //退出登录
            exitUser(msg) {
                //用户信息
                if (msg === "userInfo" && this.$route.path !== "/home") {
                    this.$router.push({ name: "home" });
                } else if (msg === "exitUser") {
                    //退出登录

                    //删除token
                    Cookie.remove("token");
                    //跳转路由
                    this.$router.push({ name: "login" });
                    //删除vuex中的用户信息
                    this.$store.commit("userInfo/REMOVEUSERINFO");
                }
            },
        },
    };
</script>

<style lang="less" scoped>
    //顶部栏总样式
    #headerArea {
        display: flex;
        justify-content: space-between;
        align-items: center;

        background: linear-gradient(90deg, #e99667, #c447ab, #06007f);
        height: 100%;
        box-shadow: 0 0 5px 1px #140582;

        //左显示区域样式
        .leftArea {
            padding: 0 0 0 10px;

            //面包屑样式
            .text {
                color: #eee;
                margin-left: 10px;
            }

            .el-button {
                border: none;
                background-color: transparent;
            }
        }

        //右侧显示区域
        .rightArea {
            //图标显示区域
            .el-dropdown-link {
                display: flex;
                align-items: center;
                padding: 0 10px 0 0;

                //图表盒子
                .iconImg {
                    display: block;
                    width: 50px;
                    height: 50px;
                    border-radius: 50%;
                    overflow: hidden;
                    //图标
                    img {
                        width: 100%;
                        vertical-align: top;
                    }
                }

                //文字样式
                h3 {
                    padding: 0 0 0 10px;
                    color: #eee;
                    font-size: 16px;
                    font-weight: 800;
                }
            }
        }
    }
</style>
