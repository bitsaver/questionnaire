package pers.yang.questionnaire.vo;

import lombok.Data;

@Data
public class RolePermissionVO {
	private Integer id;
	private Integer roleId;
	private String roleName;
	private String roleRemark;
	private Integer permissionId;
	private String permissionName;
	private String permissionRemark;
}
