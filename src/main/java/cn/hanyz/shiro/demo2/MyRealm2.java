package cn.hanyz.shiro.demo2;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;

public class MyRealm2 extends AuthenticatingRealm {

    /*返回的是一个唯一的Realm名字*/
    public String getName() {
        return "myRealm2";
    }

    public boolean supports(AuthenticationToken token) {
        /*仅支持UsernamePasswordToken类型的Token*/
        boolean b = token instanceof UsernamePasswordToken;
        return b;
    }

    public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = (String)token.getPrincipal();  //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码

        if (!"zhang".equals(username)) {
            throw new UnknownAccountException("用户名错误!");
        }

        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException("密码错误!");
        }

        /*身份验证成功,就返回一个AuthenticationInfo的实现*/
        return new SimpleAuthenticationInfo(username, password, getName());
    }

}

