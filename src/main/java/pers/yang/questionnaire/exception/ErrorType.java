package pers.yang.questionnaire.exception;

public enum ErrorType {

	UNKNOWN_ERROR(500, "未知错误!"),
	UNAUTHORIZED(401, "当前用户无权限！"),
	USER_NAME_INCORRECT(600, "用户名错误！"),
	PASSWORD_INCORRECT(601, "密码错误！"),
	NO_LOGIN(602, "未登录！"),
	AUTHENTIC_FAILED(604, "身份认证失败！"),
	INVALID_REQUEST(605, "无效的请求！"),
	EMAIL_INVALID(606, "邮箱无效！"),
	EMAIL_INACTIV(608, "邮箱未激活"),
	FAIL_SENDING_EMAIL(607, "邮箱发送失败！"),
	INVALID_CHECK_CODE(609, "验证码无效！"),
	USER_NAME_OCCUPIED(610, "用户名已被占用！"),
	INPUT_INCOMPLETE(611, "输入不完整！"),
	EMAIL_OCCUPIED(612, "邮箱已被占用！"),
	PASSWORD_INVALID(613, "密码无效！");

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