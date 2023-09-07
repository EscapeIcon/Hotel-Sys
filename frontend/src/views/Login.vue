<template>
    <div class="main">
        <div class="card">
            <div class="lines"></div>
            <div class="dataArea">
                <div class="logo">
                    <img src="../assets/logo.png" alt="" />
                </div>
                <el-form
                    status-icon
                    :label-position="labelPosition"
                    :model="formLabelAlign"
                    :rules="rules2"
                >
                    <el-form-item prop="account">
                        <el-input
                            placeholder="账号"
                            v-model="formLabelAlign.account"
                        ></el-input>
                    </el-form-item>
                    <el-form-item prop="password">
                        <el-input
                            type="password"
                            placeholder="密码"
                            v-model="formLabelAlign.password"
                        ></el-input>
                    </el-form-item>
                    <el-form-item prop="verifyCode">
                        <el-input
                            class="verify-code"
                            placeholder="验证码"
                            v-model="formLabelAlign.verifyCode"
                        ></el-input>
                        <img
                            :src="'data:image/*;base64,' + imgUrl"
                            @click="verifyCode"
                        />
                    </el-form-item>
                    <el-form-item>
                        <el-checkbox v-model="checked">记住密码</el-checkbox>
                    </el-form-item>
                    <el-form-item>
                        <el-button
                            type="primary"
                            @click="submitForm(formLabelAlign, checked)"
                            >提交</el-button
                        >
                        <el-button @click="resetForm()">重置</el-button>
                    </el-form-item>
                </el-form>
            </div>
        </div>
    </div>
</template>

<script>
    import { getVerifyCode, http } from "../api/Login.js";
    import Cookie from "js-cookie";
    export default {
        name: "Login",
        data() {
            //验证账号
            const validatePass = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error("请输入账号"));
                } else {
                    callback();
                }
            };
            //验证密码
            const validatePass2 = (rule, value, callback) => {
                if (value === "") {
                    callback(new Error("请输入密码"));
                } else {
                    callback();
                }
            };
            //验证验证码
            const checkCode = (rule, value, callback) => {
                if (value === "") {
                    return callback(new Error("验证码不能为空"));
                } else if (value !== this.verifyCodeData) {
                    return callback(new Error("验证码错误"));
                } else {
                    callback();
                }
            };
            return {
                labelPosition: "right",
                //表单数据
                formLabelAlign: {
                    account: "",
                    password: "",
                    verifyCode: "",
                },
                checked: false, //是否记住密码
                //验证规则
                rules2: {
                    account: [{ validator: validatePass, trigger: "blur" }],
                    password: [{ validator: validatePass2, trigger: "blur" }],
                    verifyCode: [{ validator: checkCode, trigger: "blur" }],
                },
                //验证码图片
                imgUrl: "",
                //uuid载器
                uuidData: "",
                //验证码
                verifyCodeData: "",
            };
        },
        methods: {
            //提交账户密码
            submitForm(val, isRembber) {
                //发送请求
                http.post("user/login", {
                    username: val.account,
                    password: val.password,
                    captcha: this.verifyCodeData,
                    uuid: this.uuidData,
                })
                    .then(res => {
                        let code = res.data.code;

                        if (code === "600") {
                            this.$message.error(res.data.msg);
                        } else {
                            //设置用户token
                            Cookie.set("token", res.data.data.token);

                            //发送vuex用户信息
                            this.$store.commit(
                                "userInfo/ADDUSERINFO",
                                res.data.data
                            );

                            //跳转到首页
                            this.$router.push("/home");
                        }
                    })
                    .catch(res => {
                        this.$message.error("登陆失败，账号、密码或验证码错误");
                    });
            },
            //重置账户密码
            resetForm() {
                for (const key in this.formLabelAlign) {
                    this.formLabelAlign[key] = "";
                }
            },
            //调用接收验证码方法
            verifyCode() {
                getVerifyCode().then(res => {
                    this.verifyCodeData = res.data.data["code"];
                    this.imgUrl = res.data.data["img"];
                    this.uuidData = res.data.data["uuid"];
                });
            },
        },
        mounted() {
            //获取验证码
            this.verifyCode();
        },
    };
</script>

<style lang="less" scoped>
    //拟body
    .main {
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
        background-size: cover;
        background: url("../assets/images/bg.jpg");

        //包裹卡片盒子
        .card {
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
            width: 400px;
            height: 500px;

            //表单区域
            .dataArea {
                display: flex;
                flex-direction: column;
                align-items: center;
                width: 68%;
                position: absolute;
                background-color: transparent;

                //包含输入框的item
                .el-form-item {
                    //验证码样式
                    img {
                        margin-left: 10px;
                        vertical-align: top;
                        width: 150px;
                        height: 40px;
                    }

                    //登录按钮样式
                    .el-button {
                        background-color: #a41698;
                        border: none;
                        opacity: 0.7;
                        transition: 0.3s;
                        color: #fff;
                        margin-left: 40px;

                        //鼠标悬浮样式
                        &:hover {
                            color: #fdca5e;
                            opacity: 1;
                        }
                    }
                }

                //验证码输入框样式
                .verify-code {
                    width: 100px;
                    height: 20px;
                }

                //logo样式
                .logo {
                    display: flex;
                    justify-content: center;
                    width: 100%;
                    height: 100px;
                    overflow: hidden;
                    img {
                        vertical-align: top;
                    }
                }
            }

            //旋转条盒子
            .lines {
                position: absolute;
                inset: 0;
                background: #b9384a;
                overflow: hidden;

                //旋转条样式
                &::before {
                    content: "";
                    position: absolute;
                    top: 50%;
                    left: 50%;
                    width: 800px;
                    height: 120px;
                    background: linear-gradient(
                        transparent,
                        #fff,
                        #fff,
                        #fff,
                        transparent
                    );
                    animation: animate 4s linear infinite;

                    //整体卡片旋转动画
                    @keyframes animate {
                        0% {
                            transform: translate(-50%, -50%) rotate(0deg);
                        }
                        100% {
                            transform: translate(-50%, -50%) rotate(360deg);
                        }
                    }
                }

                //覆盖旋转条样式
                &::after {
                    content: "";
                    position: absolute;
                    inset: 3px;
                    background: linear-gradient(-45deg, #fac856, #a41698);
                }
            }
        }
    }
</style>
