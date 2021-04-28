package pers.yang.questionnaire.testmapper;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pers.yang.questionnaire.mapper.UserMapper;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserMapperTest {

	@Resource
	UserMapper userMapper;

	@Test
	void getPermissionsByUserIdTest(){
		List<String> permissions = userMapper.getPermissionsByUserId(1);
		Assertions.assertTrue(permissions.containsAll(Arrays.asList("user:add","user:get")));
	}
}
