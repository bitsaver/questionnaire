package pers.yang.questionnaire.utils;

import org.apache.shiro.SecurityUtils;
import pers.yang.questionnaire.entity.User;

public class UserUtil {
	public static Integer getUserId(){
		return ((User)SecurityUtils.getSubject().getPrincipal()).getId();
	}
}
