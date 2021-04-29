package pers.yang.questionnaire.mapper;

import java.util.List;

import pers.yang.questionnaire.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Yang Zhenman
 * @since 2021-04-20
 */
public interface UserMapper extends BaseMapper<User> {

	List<String> getPermissionsByUserId(Integer id);

	List<String> getRolesByUserId(Integer id);

	User getUserByName(String name);

	User getByEmail(String email);
}
