package pers.yang.questionnaire.service.impl;

import java.util.List;

import javax.annotation.Resource;

import pers.yang.questionnaire.entity.RolePermission;
import pers.yang.questionnaire.mapper.RolePermissionMapper;
import pers.yang.questionnaire.service.RolePermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pers.yang.questionnaire.vo.RolePermissionVO;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2021-04-20
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {

	@Resource
	RolePermissionMapper rolePermissionMapper;
	@Override
	public RolePermissionVO getOneDtl(Integer id) {

		return rolePermissionMapper.selectDtlById(id);
	}

	@Override
	public List<RolePermissionVO> getAllDtl() {
		return rolePermissionMapper.selectDtlAll();
	}
}
