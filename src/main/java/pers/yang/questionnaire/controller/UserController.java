package pers.yang.questionnaire.controller;


import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import pers.yang.questionnaire.entity.Response;
import pers.yang.questionnaire.entity.User;
import pers.yang.questionnaire.service.UserService;
import pers.yang.questionnaire.utils.ResponseUtil;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Yang Zhenman
 * @since 2021-04-20
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Resource
	UserService userService;

	/**
	 * 添加一个用户
	 * @param user {"name","password","email"}, 用户信息，用户名密码和邮箱，此三项必填，否则报错
	 * @return 成功或失败信息
	 */
	@PostMapping
	@RequiresPermissions("user:addOne")
	public Response addOne(@RequestBody User user) {
		userService.addOne(user);
		return ResponseUtil.ok(user);
	}

	/**
	 * 根据用户编号获取用户信息
	 * @param id 用户编号
	 * @return 用户信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("user:getOne")
	public Response getOne(@PathVariable("id") Integer id) {
		User user = new User();
		user.setId(id);
		return ResponseUtil.ok(user.selectById());
	}

	/**
	 * 获取所有用户信息
	 * @return 所有用户
	 */
	@GetMapping("/")
	@RequiresPermissions("user:getAll")
	public Response getAll() {
		User user = new User();
		return ResponseUtil.ok(user.selectAll());
	}

	/**
	 * 修改用户信息不包括密码
	 * @param user 用户编号和其他用户信息
	 * @return 编辑后的用户信息
	 */
	@PutMapping("/")
	@RequiresPermissions("user:editOne")
	public Response editOne(@RequestBody User user) {
		user.setPassword(null);
		user.updateById();
		return ResponseUtil.ok(user);
	}

	/**
	 * 根据用户编号删除用户信息
	 * @param id 用户编号
	 * @return 成功或失败信息
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("user:delOne")
	public Response delOne(@PathVariable("id") Integer id) {
		User user = new User();
		user.setId(id);
		return ResponseUtil.ok(user.deleteById());
	}
}

