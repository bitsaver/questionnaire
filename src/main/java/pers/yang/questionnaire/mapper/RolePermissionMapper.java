package pers.yang.questionnaire.mapper;

import java.util.List;

import pers.yang.questionnaire.entity.RolePermission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import pers.yang.questionnaire.vo.RolePermissionVO;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yang Zhenman
 * @since 2021-04-20
 */
public interface RolePermissionMapper extends BaseMapper<RolePermission> {

	RolePermissionVO selectDtlById(Integer id);

	List<RolePermissionVO> selectDtlAll();

}
