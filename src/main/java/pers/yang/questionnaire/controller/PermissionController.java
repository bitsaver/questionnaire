package pers.yang.questionnaire.controller;


import org.apache.shiro.authz.annotation.RequiresPermissions;
import pers.yang.questionnaire.entity.Permission;
import pers.yang.questionnaire.entity.Response;
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
 *  本类用户绑定系统资源，只允许系统开发人员进行操作，对其他用户开放的接口只有查询permission:getAll
 * </p>
 *
 * @author Yang Zhenman
 * @since 2021-04-20
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

	/**
	 * 添加一个权限
	 * @param permission {"name","remark"}, 权限名和权限备注
	 * @return 成功或失败信息
	 */
	@PostMapping
	@RequiresPermissions("permission:addOne")
	public Response addOne(@RequestBody Permission permission) {
		permission.insert();
		return ResponseUtil.ok(permission.selectById());
	}

	/**
	 * 根据权限编号获取权限信息
	 * @param id 权限编号
	 * @return 权限信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("permission:getOne")
	public Response getOne(@PathVariable("id") Integer id) {
		Permission permission = new Permission();
		permission.setId(id);
		return ResponseUtil.ok(permission.selectById());
	}

	/**
	 * 获取所有权限信息
	 * @return 所有权限
	 */
	@GetMapping("/")
	@RequiresPermissions("permission:getAll")
	public Response getAll() {
		Permission permission = new Permission();
		return ResponseUtil.ok(permission.selectAll());
	}

	/**
	 * 修改一条权限信息
	 * @param permission  {"name","remark"} 权限名称和备注
	 * @return 更新后的权限信息
	 */
	@PutMapping("/")
	@RequiresPermissions("permission:editOne")
	public Response editOne(@RequestBody Permission permission) {
		permission.updateById();
		return ResponseUtil.ok(permission.selectById());
	}

	/**
	 * 根据权限编号删除权限信息
	 * @param id 权限编号
	 * @return 成功或失败信息
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("permission:delOne")
	public Response delOne(@PathVariable("id") Integer id) {
		Permission permission = new Permission();
		permission.setId(id);
		return ResponseUtil.ok(permission.deleteById());
	}

}