package pers.yang.questionnaire.config.shiro;


import java.util.List;

import javax.annotation.Resource;

import lombok.extern.java.Log;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import pers.yang.questionnaire.entity.User;
import pers.yang.questionnaire.exception.CustomException;
import pers.yang.questionnaire.exception.ErrorType;
import pers.yang.questionnaire.service.LoginService;
import pers.yang.questionnaire.service.UserService;

import org.springframework.stereotype.Component;
@Log
@Component
public class MyRealm extends AuthorizingRealm {


    @Resource
    UserService userService;

    @Resource
    LoginService loginService;

    /**
     * 当请求到达时，从数据中查询当前用户的权限，并封装成授权信息
     * @param principals 用户凭证
     * @return 用户授权信息
     * @throws RuntimeException 这里
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) throws RuntimeException{
        log.info("doGetAuthorizationInfo() ===> principals.getPrimaryPrincipal():" + principals.getPrimaryPrincipal());
        User user = (User) principals.getPrimaryPrincipal();
        List<String> permissions = loginService.getPermissionsByUserId(user.getId());
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermissions(permissions);
        log.info("授予权限:" + permissions.toString());
        return simpleAuthorizationInfo;
    }

    /**
     * 登录验证
     * 当请求到达时，从数据库中查找用户信息，并封装成登录信息，shiro自动将用户认证信息中的密码和Token中的密码匹配
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws RuntimeException {
        log.info("doGetAuthenticationInfo() ===> getPrincipal:  " + authenticationToken.getPrincipal());

        User user = userService.getUserByName((String) authenticationToken.getPrincipal());

        if(user == null)
            throw new CustomException(ErrorType.INCORRECT_ID);

        return new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                ByteSource.Util.bytes(user.getPasswordSalt()),
                getName()
        );
    }
}