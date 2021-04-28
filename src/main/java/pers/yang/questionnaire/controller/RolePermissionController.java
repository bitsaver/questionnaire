package pers.yang.questionnaire.controller;


import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import pers.yang.questionnaire.entity.Response;
import pers.yang.questionnaire.entity.RolePermission;
import pers.yang.questionnaire.service.RolePermissionService;
import pers.yang.questionnaire.utils.ResponseUtil;
import pers.yang.questionnaire.vo.RolePermissionVO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/rolePermission")
public class RolePermissionController {

	@Resource
	RolePermissionService rolePermissionService;


	/**
	 * 授予角色权限
	 * @param rolePermission {"roleId","permissionId"}，角色编号和权限编号
	 * @return 成功或失败信息
	 */
	@PostMapping
	@RequiresPermissions("rolePermission:addOne")
	public Response addOne(@RequestBody RolePermission rolePermission) {
		rolePermission.insert();
		return ResponseUtil.ok(rolePermission.selectById());
	}

	/**
	 * 根据角色权限编号获取角色权限详细信息
	 * @param id 角色权限编号
	 * @return 角色权限信息
	 */
	@GetMapping("/{id}")
	@RequiresPermissions("rolePermission:getOne")
	public Response getOne(@PathVariable("id") Integer id) {
		RolePermissionVO rolePermissionVO = rolePermissionService.getOneDtl(id);
		return ResponseUtil.ok(rolePermissionVO);
	}

	/**
	 * 获取所有角色权限详细信息
	 * @return 所有权限
	 */
	@GetMapping("/")
	@RequiresPermissions("rolePermission:getAll")
	public Response getAll() {
		List<RolePermissionVO> rolePermissionVOList = rolePermissionService.getAllDtl();
		return ResponseUtil.ok(rolePermissionVOList);
	}


	/**
	 * 根据角色权限编号消除角色权限
	 * @param id 权限编号
	 * @return 成功或失败信息
	 */
	@DeleteMapping("/{id}")
	@RequiresPermissions("rolePermission:delOne")
	public Response delOne(@PathVariable("id") Integer id) {
		RolePermission rolePermission = new RolePermission();
		rolePermission.setId(id);
		return ResponseUtil.ok(rolePermission.deleteById());
	}


}

