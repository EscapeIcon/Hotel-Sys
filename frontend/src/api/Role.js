import http from "../utils/request";

//获取角色信息
const getUserInfo = () => {
    return http.get("role");
};

export { http, getUserInfo };
