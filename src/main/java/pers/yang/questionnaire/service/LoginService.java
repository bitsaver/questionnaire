package pers.yang.questionnaire.service;

import java.util.List;

import pers.yang.questionnaire.entity.User;

public interface LoginService {

	List<String> getPermissionsByUserId(Integer id);

	void login(User user);

	List<String> getRolesByUserId(Integer id);
}
