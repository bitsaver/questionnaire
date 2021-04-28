package pers.yang.questionnaire.exception;

public enum  ErrorType {


    UNKNOWN_ERROR(500,"未知错误!"),

    UNAUTHORIZED(401, "当前用户无权限！"),

    INCORRECT_ID(600, "账号错误！"),

    INCORRECT_PASSWORD(601,"密码错误！"),

    NO_LOGIN(602,"未登录！"),

    AUTHENTIC_FAILED(604, "身份认证失败！"),

    INVALID_REQUEST(605, "无效的请求！" );

    private int code;

    private String msg;

    ErrorType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}