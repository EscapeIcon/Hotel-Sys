<template>
    <div>
        <!-- 添加栏 -->
        <el-form :inline="true" :model="addResult" class="demo-form-inline">
            <el-form-item label="角色名称">
                <el-input
                    v-model="addResult.name"
                    placeholder="名称"
                ></el-input>
            </el-form-item>
            <el-form-item label="roleKey">
                <el-input
                    v-model="addResult.roleKey"
                    placeholder="roleKey"
                ></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" @click="addSubmit">添加</el-button>
            </el-form-item>
        </el-form>
        <!-- 查询栏 -->
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item label="角色名称">
                <el-input v-model="userName" placeholder="名称"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="queryUserFun">查询</el-button>
                <el-button type="primary" @click="onClear">清空</el-button>
            </el-form-item>
        </el-form>
        <!-- 展示栏 -->
        <el-table
            :data="userInfo"
            style="width: 100%"
            :row-class-name="tableRowClassName"
        >
            <!-- id -->
            <el-table-column prop="id" label="角色id" width="150">
            </el-table-column>
            <!-- 名称 -->
            <el-table-column label="角色名称" width="300">
                <template slot-scope="scope">
                    <el-input
                        v-if="colNum === 2 && rowNum === scope.$index"
                        ref="inputTitle"
                        v-model="scope.row.name"
                        placeholder="请输入内容"
                        @blur="removeColRow"
                        @change="postRoleInfo(scope.row)"
                    ></el-input>
                    <p v-else @click="editUser(2, scope.$index)">
                        {{ scope.row.name }}
                    </p>
                </template>
            </el-table-column>
            <!-- roleKey -->
            <el-table-column label="rokeKey" width="300">
                <template slot-scope="scope">
                    <el-input
                        v-if="colNum === 3 && rowNum === scope.$index"
                        ref="inputTitle"
                        v-model="scope.row.roleKey"
                        placeholder="请输入内容"
                        @blur="removeColRow"
                        @change="postRoleInfo(scope.row)"
                    ></el-input>
                    <p v-else @click="editUser(3, scope.$index)">
                        {{ scope.row.roleKey }}
                    </p>
                </template>
            </el-table-column>
            <!-- 删除 -->
            <el-table-column label="删除">
                <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="danger"
                        @click="handleDelete(scope.row)"
                        >删除</el-button
                    >
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页栏 -->
        <el-pagination
            :page-size="pageSize"
            :current-page.sync="pageNum"
            layout="prev, pager, next"
            :total="50"
            @current-change="currentChange"
        >
        </el-pagination>
    </div>
</template>

<script>
    import { http, getUserInfo } from "../api/Role.js";
    export default {
        name: "Home",
        data() {
            return {
                //添加的角色信息
                addResult: {
                    name: "",
                    roleKey: "",
                },
                userName: "", //查询的角色信息
                pageNum: 1, //当前页
                pageSize: 10, //每页显示的条数
                //获取到的角色信息
                userInfo: [],
                colNum: 0, //列
                rowNum: 0, //行
            };
        },
        methods: {
            //查询
            queryUserFun() {
                http.get("role/page", {
                    params: {
                        name: this.userName,
                        pageNum: this.pageNum,
                        pageSize: this.pageSize,
                    },
                }).then(res => {
                    this.userInfo = res.data.data.records;
                });
            },
            //清空
            onClear() {
                for (const key in this.queryUser) {
                    this.queryUser[key] = "";
                }
            },
            //添加role信息
            addSubmit() {
                //发送post请求添加角色
                http.post("role", this.addResult)
                    .then(res => {
                        this.$$message.success("添加成功");
                        this.queryUserFun();
                    })
                    .catch(res => {
                        this.$$message.error("请求服务器失败，你是不是没网？");
                    });
            },

            //删除
            handleDelete(target) {
                //修改本地角色数据
                this.userInfo = this.userInfo.filter(
                    item => item.id !== target.id
                );
                //发送删除请求
                http.delete(`role/${target.id}`)
                    .then(res => {
                        this.$message.success("删除成功");
                    })
                    .catch(res => {
                        this.$message.error("删除失败");
                    });
            },

            //修改当前行列位置
            editUser(col, row) {
                (this.colNum = col), (this.rowNum = row);
                //自动获取焦点
                this.$nextTick(() => {
                    this.$refs.inputTitle.focus();
                });
            },
            //重置行列
            removeColRow() {
                (this.colNum = 0), (this.rowNum = 0);
            },

            //发送修改角色的请求
            postRoleInfo(target) {
                http.post("role", target)
                    .then(res => {
                        this.$message.success("修改成功");
                    })
                    .catch(res => {
                        this.$message.error("修改失败，你是不是没网！");
                    });
            },

            //当前页面改变时
            currentChange() {
                this.queryUserFun();
            },

            //饿了么的跟我没关系
            tableRowClassName({ row, rowIndex }) {
                if (rowIndex === 1) {
                    return "warning-row";
                } else if (rowIndex === 3) {
                    return "success-row";
                }
                return "";
            },
        },
        mounted() {
            //开局直接请求所有角色信息
            getUserInfo().then(res => {
                console.log(res.data.data);
                this.userInfo = res.data.data;
            });
        },
    };
</script>

<style></style>
