<template>
    <div id="home">
        <el-col :span="9">
            <!-- 登录用户信息显示区域 -->
            <el-card class="box-card">
                <!-- 上部分用户信息 -->
                <div class="headerUserInfo">
                    <!-- 用户头像 -->
                    <div class="img">
                        <img :src="userData.avatarUrl" />
                    </div>
                    <div class="userInfo">
                        <!-- 用户name -->
                        <h3>
                            用户名称：<span>{{ userData.username }}</span>
                        </h3>
                        <!-- 用户昵称 -->
                        <p>
                            用户昵称：<span>{{ userData.nickname }}</span>
                        </p>
                    </div>
                </div>
                <!-- 下部分用户信息显示与否 -->
                <el-button
                    type="info"
                    size="mini"
                    @click="show3 = !show3"
                    :class="!show3 ? 'el-icon-arrow-down' : 'el-icon-arrow-up'"
                >
                    显示详细信息
                </el-button>
                <!-- 下部信息 -->
                <el-collapse-transition>
                    <div class="userDetailInfo" v-show="show3">
                        <p>
                            用户电话：<span>{{ userData.phone }}</span>
                        </p>
                        <p>
                            用户创建时间：<span>{{ userData.createTime }}</span>
                        </p>
                        <p>
                            用户角色：<span>{{ userRole.name }}</span>
                        </p>
                    </div>
                </el-collapse-transition>
            </el-card>
            <!-- 额~~~ -->
            <div class="img1">
                <img src="../assets/images/bg2.png" alt="" />
            </div>
        </el-col>
        <el-col :span="14">
            <h1>欢迎{{ userData.username }}登录</h1>
            <span>玩会贪吃蛇吧？</span>
            <iframe src="http://116.204.74.130/" frameborder="0"></iframe>
        </el-col>
    </div>
</template>

<script>
    import { mapState } from "vuex";
    export default {
        name: "Home",
        data() {
            return {
                //是否展开信息栏
                show3: false,
            };
        },
        computed: {
            //获取用户总体信息
            ...mapState("userInfo", ["userData"]),
            ...mapState("userInfo", ["userRole"]),
        },
    };
</script>

<style lang="less" scoped>
    #home {
        //登录用户信息显示区域
        .el-card {
            width: 452px;
            background: linear-gradient(60deg, #e99667, #c246ab, #9f39a3);
            //上部分用户信息
            .headerUserInfo {
                display: flex;
                align-items: center;
                justify-content: space-around;
                transition: 0.1s;
                //图片盒子
                .img {
                    min-width: 200px;
                    overflow: hidden;
                    border-radius: 100%;
                    //图片样式
                    img {
                        vertical-align: top;
                        width: 200px;
                        height: 200px;
                    }
                }
                //用户信息
                .userInfo {
                    margin-left: 10px;
                    min-width: 100px;
                    color: #fff;
                    white-space: nowrap;
                    h3 {
                        padding: 10px 0 10px 0;
                    }
                    p {
                        text-align: center;
                    }
                }
            }
            //显示下部分按钮
            .el-button {
                margin-top: 10px;
                background-color: transparent;
                border: none;
                //鼠标悬浮样式
                &:hover {
                    color: #b942a9;
                }
            }
            //用户详细信息
            .userDetailInfo {
                p {
                    padding: 10px 0 0 0;
                    color: #fff;
                }
            }

            //鼠标悬浮事件
            &:hover {
                box-shadow: 0 0 10px 2px #c96983;
                border: none;
            }
        }

        .img1 {
            display: inline-block;
            margin-top: 20px;
            border-radius: 30px;
            overflow: hidden;
            img {
                vertical-align: top;
            }

            &:hover {
                transition: 0.5s;
                transform: rotateZ(360deg);
            }
        }
        iframe {
            width: 100%;
            height: 700px;
        }
    }
</style>
