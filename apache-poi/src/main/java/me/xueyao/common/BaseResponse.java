package me.xueyao.common;

/**
 * @author: Simon.Xue
 * @date: 2019/4/16 11:43
 */
public class BaseResponse {
    private Integer code;
    private String msg;
    private Object data;


    public BaseResponse() {
    }

    public BaseResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResponse(BaseStatus status) {
        this.code = status.getCode();
        this.msg = status.getMsg();
    }

    public BaseResponse(BaseStatus status, String msg) {
        this.code = status.getCode();
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
