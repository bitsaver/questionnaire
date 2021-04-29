package pers.yang.questionnaire.service;

import pers.yang.questionnaire.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Yang Zhenman
 * @since 2021-04-20
 */
public interface UserService extends IService<User> {

	User getUserByName(String name);

	Boolean addOne(User user);


	Boolean editOne(User user);

	User getUserByEmail(String email);
}
