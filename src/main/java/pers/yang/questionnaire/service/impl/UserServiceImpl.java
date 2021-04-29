package pers.yang.questionnaire.service.impl;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import lombok.extern.java.Log;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.DefaultHashService;
import pers.yang.questionnaire.entity.User;
import pers.yang.questionnaire.mapper.UserMapper;
import pers.yang.questionnaire.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.yang.questionnaire.utils.UserUtil;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2021-04-20
 */
@Log
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


	@Resource
	UserMapper userMapper;

	/**
	 * 根据用户名找到用户信息
	 * @param name 用户名
	 * @return 一个用户信息
	 */
	@Override
	public User getUserByName(String name) {
		return userMapper.getUserByName(name);
	}

	/**
	 * 添加一个账号，赋予该账号基础角色的权限，添加角色由触发器进行设置
	 * @param user 用户信息，包括用户名、密码和邮箱
	 * @return 是否成功
	 */
	@Override
	public Boolean addOne(User user) {
		encryptUser(user);
		user.setAddTime(LocalDateTime.now());
		return user.insert();
	}

	/**
	 * 对用户的明文密码以新的盐值进行加密生成密文密码，将用户的密码和密码盐值设为密文密码和新密码盐值
	 * @param user 明文user
	 */
	private void encryptUser(User user) {
		RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
		Object salt = randomNumberGenerator.nextBytes();
		//明文密码
		String plainTextPassword = user.getPassword();
		DefaultHashService hashService = new DefaultHashService();
		hashService.setRandomNumberGenerator(randomNumberGenerator);
		DefaultPasswordService passwordService = new DefaultPasswordService();
		passwordService.setHashService(hashService);
		//密文密码
		String encryptPassword = passwordService.encryptPassword(plainTextPassword);
		//设置用户的加密密码和密码盐值
		user.setPassword(encryptPassword);
		user.setPasswordSalt(salt.toString());
	}

	/**
	 * 编辑一个账号，
	 * @param user 用户信息，包括用户名、密码和邮箱
	 * @return 是否成功
	 */
	@Override
	public Boolean editOne(User user) {
		encryptUser(user);
		return user.updateById();
	}

	@Override
	public User getUserByEmail(String email) {
		return userMapper.getByEmail(email);
	}

}
