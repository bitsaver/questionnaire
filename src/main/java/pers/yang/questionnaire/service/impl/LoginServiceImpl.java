package pers.yang.questionnaire.service.impl;

import java.util.List;

import javax.annotation.Resource;

import lombok.extern.java.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import pers.yang.questionnaire.entity.User;
import pers.yang.questionnaire.mapper.UserMapper;
import pers.yang.questionnaire.service.LoginService;

import org.springframework.stereotype.Service;
@Log
@Service
public class LoginServiceImpl implements LoginService {

	@Resource
	UserMapper userMapper;

	/**
	 * 通过用户编号获取用户的所有权限
	 * @param id 用户编号
	 * @return 用户所有权限
	 */
	@Override
	public List<String> getPermissionsByUserId(Integer id) {
		return userMapper.getPermissionsByUserId(id);
	}

	/**
	 * 使用用户和密码登录系统
	 * @param user 用户名和密码
	 */
	@Override
	public void login(User user) {
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword());
		SecurityUtils.getSubject().login(token);
	}


}