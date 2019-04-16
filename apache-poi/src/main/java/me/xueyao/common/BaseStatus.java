package me.xueyao.common;

/**
 * @Description:
 * @Author: Simon.Xue
 * @Date: 2019/4/16 11:44
 */
public enum BaseStatus {
    SUCCESS(200,"请求成功"),
    BADPARAM(401,"请求参数有误"),
    EXECPTION(400, "业务处理失败");

    int code;
    String msg;

    BaseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
