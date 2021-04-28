package pers.yang.questionnaire.service;

import java.util.List;

import pers.yang.questionnaire.entity.RolePermission;
import com.baomidou.mybatisplus.extension.service.IService;
import pers.yang.questionnaire.vo.RolePermissionVO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2021-04-20
 */
public interface RolePermissionService extends IService<RolePermission> {

	RolePermissionVO getOneDtl(Integer id);

	List<RolePermissionVO> getAllDtl();
}
