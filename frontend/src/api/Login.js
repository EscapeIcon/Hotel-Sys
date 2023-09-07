import http from "@/utils/request";

//获取验证码
const getVerifyCode = () => {
    return http.get("/common/captchaImage");
};

export { getVerifyCode, http };
