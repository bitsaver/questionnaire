package pers.yang.questionnaire.controller;

import javax.annotation.Resource;

import lombok.extern.java.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import pers.yang.questionnaire.entity.Response;
import pers.yang.questionnaire.entity.User;
import pers.yang.questionnaire.exception.ErrorType;
import pers.yang.questionnaire.service.LoginService;
import pers.yang.questionnaire.service.UserService;
import pers.yang.questionnaire.utils.ResponseUtil;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class LoginController {

	@Resource
	LoginService loginService;

	@Resource
	UserService userService;

	/**
	 * 登录系统
	 * @param user 登录信息，{name,password},包括用户名，密码
	 * @return 成功或错误信息
	 */
	@PostMapping("/login")
	public Response login(@RequestBody User user){
		loginService.login(user);
		return ResponseUtil.ok();
	}

	/**
	 * 注册账号，需要输入用户名密码和邮箱，此三项必填，否则报错
	 * @param user 用户注册信息，{name,password,email},包括用户名，密码和邮箱email.
	 * @return 重定向到登录页面.
	 */
	@PostMapping("/signup")
	public Response signup(@RequestBody User user){
		userService.addOne(user);
		return ResponseUtil.ok(user);
	}

	/**
	 * 退出登录
	 * @return 成功或错误信息
	 */
	@GetMapping("/logout")
	public Response logout(){
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()){
			subject.logout();
			return ResponseUtil.ok();
		}
		return ResponseUtil.ok(ErrorType.NO_LOGIN);
	}

	/**
	 * 修改用户密码
	 * @param user 用户新密码
	 * @param checkCode 验证码
	 * @return 成功或错误信息
	 */
	@PutMapping("/password")
	public Response password(@RequestBody User user,String checkCode){
		userService.editOne(user);
		return ResponseUtil.ok(user);
	}
}
