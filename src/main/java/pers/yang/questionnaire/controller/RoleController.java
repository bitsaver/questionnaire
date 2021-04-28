package pers.yang.questionnaire.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import pers.yang.questionnaire.entity.Response;
import pers.yang.questionnaire.entity.Role;
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
@RequestMapping("/role")
public class RoleController {

	/**
	 * 添加一个角色
	 * @param role {"name","remark"}, 角色名和角色备注
	 * @return 成功或失败信息
	 */
	@PostMapping
	@RequiresPermissions("role:addOne")
	public Response addOne(@RequestBody Role role) {
		role.insert();
		return ResponseUtil.ok(role.selectById());
	}

	/**
	 * 根据角色编号获取角色信息
	 * @param id 角色编号
	 * @return 角色信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("role:getOne")
	public Response getOne(@PathVariable("id") Integer id) {
		Role role = new Role();
		role.setId(id);
		return ResponseUtil.ok(role.selectById());
	}

	/**
	 * 获取所有角色信息
	 * @return 所有角色
	 */
	@GetMapping("/")
	@RequiresPermissions("role:getAll")
	public Response getAll() {
		Role role = new Role();
		return ResponseUtil.ok(role.selectAll());
	}

	/**
	 * 修改一条角色信息
	 * @param role  {"name","remark"} 角色名称和备注
	 * @return 更新后的角色信息
	 */
	@PutMapping("/")
	@RequiresPermissions("role:editOne")
	public Response editOne(@RequestBody Role role) {
		role.updateById();
		return ResponseUtil.ok(role.selectById());
	}

	/**
	 * 根据角色编号删除角色信息
	 * @param id 角色编号
	 * @return 成功或失败信息
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("role:delOne")
	public Response delOne(@PathVariable("id") Integer id) {
		Role role = new Role();
		role.setId(id);
		return ResponseUtil.ok(role.deleteById());
	}

}

