package pers.yang.questionnaire.utils;

import pers.yang.questionnaire.entity.Response;
import pers.yang.questionnaire.exception.CustomException;
import pers.yang.questionnaire.exception.ErrorType;


public class ResponseUtil {

	private static final String SUCCESS = "操作成功！";

	public static Response ok(Object obj) {
		Response res = new Response();
		res.setCode(200);
		res.setMsg(SUCCESS);
		res.setData(obj);
		return res;
	}

	public static Response ok() {
		return ResponseUtil.ok(null);
	}

	public static Response error(ErrorType errorType) {
		Response res = new Response();
		res.setCode(errorType.getCode());
		res.setMsg(errorType.getMsg());
		return res;
	}

	public static Response error(CustomException ce) {
		Response res = new Response();
		res.setCode(ce.getCode());
		res.setMsg(ce.getMessage());
		return res;
	}

}