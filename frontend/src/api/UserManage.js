import http from "@/utils/request";

//获取所有用户权限
const getRole = () => {
    return http.get("role");
};

export { http, getRole };
