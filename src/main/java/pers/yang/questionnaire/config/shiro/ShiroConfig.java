package pers.yang.questionnaire.config.shiro;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {


	@Bean
	public DefaultHashService defaultHashService() {
		return new DefaultHashService();
	}

	@Bean
	public DefaultPasswordService defaultPasswordService(@Autowired DefaultHashService defaultHashService) {
		DefaultPasswordService passwordService = new DefaultPasswordService();
		passwordService.setHashService(defaultHashService);
		return passwordService;
	}

	/**
	 * Real的加密匹配是算法
	 * @return 密码匹配器
	 */
	@Bean
	public PasswordMatcher passwordMatcher(@Autowired DefaultPasswordService defaultPasswordService) {
		PasswordMatcher matcher = new PasswordMatcher();
		matcher.setPasswordService(defaultPasswordService);
		return matcher;
	}

	@Bean
	public Realm realm(@Autowired PasswordMatcher passwordMatcher) {
		MyRealm myRealm = new MyRealm();
		myRealm.setCredentialsMatcher(passwordMatcher);
		return myRealm;
	}

	@Bean("securityManager")
	public DefaultWebSecurityManager defaultSecurityManager(@Autowired Realm realm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(realm);
		return securityManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(@Autowired DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

		filterFactoryBean.setSecurityManager(securityManager);

		Map<String,String> filterMap = new HashMap<>();
		filterMap.put("/login", "anon");
		filterMap.put("/signup","anon");
		filterMap.put("/logout","anon");
		filterFactoryBean.setFilterChainDefinitionMap(filterMap);

		return filterFactoryBean;
	}
}