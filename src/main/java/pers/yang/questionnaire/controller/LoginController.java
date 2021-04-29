package pers.yang.questionnaire.controller;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import lombok.extern.java.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import pers.yang.questionnaire.entity.Response;
import pers.yang.questionnaire.entity.User;
import pers.yang.questionnaire.exception.CustomException;
import pers.yang.questionnaire.exception.ErrorType;
import pers.yang.questionnaire.service.LoginService;
import pers.yang.questionnaire.service.UserService;
import pers.yang.questionnaire.utils.CheckCodeUtil;
import pers.yang.questionnaire.utils.ResponseUtil;
import pers.yang.questionnaire.vo.UserVO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log
@RestController
public class LoginController {

	@Resource
	LoginService loginService;

	@Resource
	UserService userService;

	@Resource
	CheckCodeUtil checkCodeUtil;

	/**
	 * 登录系统
	 * @param user 登录信息，{name,password},包括用户名，密码
	 * @return 成功或错误信息
	 */
	@PostMapping("/login")
	public Response login(@RequestBody User user) {
		loginService.login(user);
		return ResponseUtil.ok();
	}

	/**
	 * 生成并保存验证码，然后通过邮箱将验证码发送给用户
	 * @param email 电子邮件
	 * @return 提示信息
	 */
	@GetMapping("/checkCode/{email}")
	public Response checkCode(@PathVariable(name = "email") String email) {
		try {
			checkCodeUtil.genAndsend(email);
		}
		catch (MessagingException e) {
			e.printStackTrace();
			throw new CustomException(ErrorType.FAIL_SENDING_EMAIL);
		}
		return ResponseUtil.ok("发送成功，请登录邮箱查看验证码");
	}

	/**
	 * 注册账号，需要输入用户名、密码和邮箱，此三项必填，否则报错
	 * @param userVO 验证码、用户名、密码和邮箱
	 * @return 新用户信息
	 */

	@PostMapping("/signup")
	public Response signup(@RequestBody UserVO userVO) {
		Integer checkCode = userVO.getCheckCode();
		if (checkCode == null) {
			throw new CustomException(ErrorType.INVALID_CHECK_CODE);
		}

		User user = userVO;
		if (userService.getUserByName(userVO.getName().trim()) != null) {
			throw new CustomException(ErrorType.USER_NAME_OCCUPIED);
		}
		if (userService.getUserByEmail(userVO.getEmail().trim()) != null) {
			throw new CustomException(ErrorType.EMAIL_OCCUPIED);
		}
		if (checkCode.equals(checkCodeUtil.getByEmail(user.getEmail()))) {
			userService.addOne(user);
			return ResponseUtil.ok(user);
		}
		else {
			throw new CustomException(ErrorType.INVALID_CHECK_CODE);
		}
	}

	/**
	 * 退出登录
	 * @return 成功或错误信息
	 */
	@DeleteMapping("/logout")
	public Response logout() {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated()) {
			throw new CustomException(ErrorType.NO_LOGIN);
		}
		subject.logout();
		return ResponseUtil.ok();
	}

	/**
	 * 修改密码，账号主需要邮箱验证码才能修改，管理员不需要。
	 * 1. 判断账号存在
	 * 2. 判断是否为管理员，若是直接修改，否则判断验证码是否匹配，若不匹配则错误
	 * @param userVO {email:邮箱,password 新密码,checkCode:从邮箱中收到的验证码}
	 * @return 新的用户信息
	 */
	@PutMapping("/password")
	public Response password(@RequestBody UserVO userVO) {
		Integer checkCode = userVO.getCheckCode();
		User user = userVO;
		User isExist = userService.getUserByEmail(user.getEmail().trim());
		if (isExist == null) {
			throw new CustomException(ErrorType.EMAIL_INACTIV);
		}
		user.setId(isExist.getId());
		if (SecurityUtils.getSubject().hasRole("admin")) {
			log.fine("管理员修改用户密码");
			userService.editOne(user);
		}
		else if (checkCode != null && checkCodeUtil.getByEmail(user.getEmail().trim()).equals(checkCode)) {
			log.fine("账号主人修改密码");
			userService.editOne(user);
		}
		else {
			throw new CustomException(ErrorType.INVALID_CHECK_CODE);
		}
		return ResponseUtil.ok(user.selectById());
	}
}
