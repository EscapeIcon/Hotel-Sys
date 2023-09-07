<template>
    <div id="user">
        <!-- 添加栏 -->
        <el-form :inline="true" :model="addResult" class="demo-form-inline">
            <el-form-item label="用户名">
                <el-input
                    v-model="addResult.username"
                    placeholder="用户名"
                ></el-input>
            </el-form-item>
            <el-form-item label="昵称">
                <el-input
                    v-model="addResult.nickname"
                    placeholder="昵称"
                ></el-input>
            </el-form-item>
            <el-form-item label="手机号">
                <el-input
                    v-model="addResult.phone"
                    placeholder="手机号"
                ></el-input>
            </el-form-item>
            <el-form-item label="角色名称">
                <el-select v-model="addResult.role.name" placeholder="角色名称">
                    <el-option
                        v-for="(item, index) in roleInfo"
                        :key="index"
                        :label="item.name"
                        :value="item.name"
                    ></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="addSubmit">添加</el-button>
            </el-form-item>
        </el-form>
        <!-- 查询栏 -->
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item label="用户名">
                <el-input
                    v-model="formInline.username"
                    placeholder="用户名"
                ></el-input>
            </el-form-item>
            <el-form-item label="昵称">
                <el-input
                    v-model="formInline.nickname"
                    placeholder="昵称"
                ></el-input>
            </el-form-item>
            <el-form-item label="电话号码">
                <el-input
                    v-model="formInline.phone"
                    placeholder="电话号码"
                ></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="onSubmit">查询</el-button>
                <el-button type="primary" @click="onClear">清空</el-button>
            </el-form-item>
        </el-form>

        <!-- 用户显示栏 -->
        <el-table :data="allUserInfo" style="width: 100%">
            <!-- 用户id -->
            <el-table-column label="用户id" width="100">
                <template slot-scope="scope">
                    <span style="margin-left: 10px">{{ scope.row.id }}</span>
                </template>
            </el-table-column>
            <!-- 姓名 -->
            <el-table-column label="用户名" width="150">
                <template slot-scope="scope">
                    <el-input
                        ref="inputTitle"
                        v-if="colIndex === 1 && rowIndex === scope.$index"
                        @change="postUserInfo(scope.row)"
                        @blur="removeEvent"
                        v-model="scope.row.username"
                        placeholder="请输入内容"
                    ></el-input>
                    <p v-else @click="editSingleInfo(1, scope.$index)">
                        {{ scope.row.username }}
                    </p>
                </template>
            </el-table-column>
            <!-- 用户昵称 -->
            <el-table-column label="昵称" width="150">
                <template slot-scope="scope">
                    <el-input
                        ref="inputTitle"
                        v-if="colIndex === 2 && rowIndex === scope.$index"
                        @change="postUserInfo(scope.row)"
                        @blur="removeEvent"
                        v-model="scope.row.nickname"
                        placeholder="请输入内容"
                    ></el-input>
                    <p v-else @click="editSingleInfo(2, scope.$index)">
                        {{ scope.row.nickname }}
                    </p>
                </template>
            </el-table-column>
            <!-- 手机号 -->
            <el-table-column label="手机号" width="150">
                <template slot-scope="scope">
                    <el-input
                        ref="inputTitle"
                        v-if="colIndex === 3 && rowIndex === scope.$index"
                        @change="postUserInfo(scope.row)"
                        @blur="removeEvent"
                        v-model="scope.row.phone"
                        placeholder="请输入内容"
                    ></el-input>
                    <p v-else @click="editSingleInfo(3, scope.$index)">
                        {{ scope.row.phone }}
                    </p>
                </template>
            </el-table-column>
            <!-- 注册时间 -->
            <el-table-column label="注册时间" width="200">
                <template slot-scope="scope">
                    <p>{{ scope.row.createTime }}</p>
                </template>
            </el-table-column>
            <!-- 角色名称 -->
            <el-table-column label="角色名称" width="200">
                <template slot-scope="scope">
                    <el-select
                        v-model="scope.row.role.name"
                        @change="changeRoleAcc(scope.row)"
                        placeholder="请选择"
                    >
                        <el-option
                            v-for="item in roleInfo"
                            :key="item.id"
                            :label="item.name"
                            :value="item.name"
                        >
                        </el-option>
                    </el-select>
                </template>
            </el-table-column>
            <!-- 用户状态 -->
            <el-table-column label="用户状态" width="200">
                <template slot-scope="scope">
                    <el-switch
                        v-model="scope.row.status"
                        active-color="#13ce66"
                        inactive-color="#ff4949"
                        @change="postUserInfo(scope.row)"
                        :active-value="1"
                        :inactive-value="0"
                    >
                    </el-switch>
                </template>
            </el-table-column>
            <!-- 删除 -->
            <el-table-column label="操作">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.$index, scope.row)"
                        >删除</el-button
                    >
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页组件 -->
        <div class="block">
            <el-pagination
                @current-change="handleCurrentChange"
                :current-page.sync="pageNum"
                :page-size="pageSize"
                layout="prev, pager, next"
                :total="50"
            >
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import { http, getRole } from "../api/UserManage.js";
    export default {
        name: "User",
        data() {
            return {
                //表单查询&&添加结果
                formInline: {
                    username: "",
                    nickname: "", //昵称
                    phone: "", //手机号
                },
                //添加结果
                addResult: {
                    username: "",
                    nickname: "", //昵称
                    phone: "", //手机号
                    role: {
                        id: 0,
                        name: "",
                        roleKey: "",
                    },
                },
                allUserInfo: [], //所有用户信息
                rowIndex: 0, //当前修改的列
                colIndex: 0, //当前修改的行
                pageSize: 10, //每页显示的条数
                pageNum: 1, //当前页
                //role信息
                roleInfo: [],
            };
        },
        methods: {
            //查询
            onSubmit() {
                //通过get发送请求是拿params来传参的
                http.get("user/page", {
                    params: {
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                        username: this.formInline.username,
                        nickname: this.formInline.nickname,
                        phone: this.formInline.phone,
                    },
                })
                    .then(res => {
                        //接收请求到的所有用户信息
                        this.allUserInfo = res.data.data.records.map(item => {
                            //如果role为null，我就自己给他加一个obj
                            if (!item.role) {
                                item.role = {
                                    id: "",
                                    name: "",
                                    roleKey: "",
                                };
                            }
                            return item;
                        });
                    })
                    .catch(result => {
                        this.$message.error("请求出现错误~~~");
                    });
            },
            //添加
            addSubmit() {
                //判断进行role的添加（可改进）
                let role = this.addResult.role;
                for (const item of this.roleInfo) {
                    if (item.name === role.name) {
                        this.addResult.role = item;
                    }
                }
                this.postUserInfo(this.addResult);
            },
            //清空
            onClear() {
                for (const key in this.formInline) {
                    this.formInline[key] = "";
                }
                this.onSubmit();
            },

            //删除用户信息
            handleDelete(index, row) {
                //发送删除请求
                http.delete(`user/${this.allUserInfo[index].id}`)
                    .then(res => {
                        this.$$message.success("删除成功");
                    })
                    .catch(res => {
                        this.$message.error("删除失败");
                    });
                //更新本地表单信息
                this.allUserInfo = this.allUserInfo.filter(
                    (item, key) => key !== index
                );
            },
            //编辑用户信息
            editSingleInfo(col, row) {
                this.colIndex = col; //修改列
                this.rowIndex = row; //修改行
                //自动获取焦点
                this.$nextTick(() => {
                    this.$refs.inputTitle.focus();
                });
            },
            //失去焦点事件
            removeEvent(index, target) {
                this.colIndex = 0; //重置列
                this.rowIndex = 0; //重置行
            },

            //更改用户角色名称的方法
            changeRoleAcc(target) {
                for (const item of this.roleInfo) {
                    if (item.name === target.role.name) {
                        target.role = item;
                    }
                }

                this.postUserInfo(target);
            },

            //发送用户数据
            postUserInfo(target) {
                console.log(target);
                //请求数据
                http.post("user", target).then(res => {
                    let code = res.data.code;
                    if (code === "200") {
                        this.$message.success("修改成功");
                        this.onSubmit();
                    } else {
                        this.$message.error("失败了");
                    }
                });
            },

            //当前页面改变
            handleCurrentChange(val) {
                this.pageNum = val;
                this.onSubmit();
            },
        },
        mounted() {
            //开局查询所有用户信息
            this.onSubmit();
            //获取角色信息
            getRole().then(res => {
                this.roleInfo = res.data.data;
            });
        },
    };
</script>

<style lang="less" scoped>
    #user {
        //查询区域
        .el-form {
            min-width: 1200px;
        }
    }
</style>
