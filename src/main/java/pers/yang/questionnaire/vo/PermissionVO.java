package pers.yang.questionnaire.vo;

import lombok.Data;

@Data
public class PermissionVO {
	private Integer id;
	private String roleName;
	private String roleRemark;
	private String permissionName;
	private String permissionRemark;
}
