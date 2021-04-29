package pers.yang.questionnaire.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import pers.yang.questionnaire.entity.User;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserVO extends User {
	private Integer checkCode;
}
