package cn.hanyz.shiro.demo2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;


/**
 * @param
 * @author han
 * @date 18/4/12 下午7:51
 * @return
 */
public class Demo2 {

    public static void main(String[] args) {

        /*获取SecurityManagerFactory工厂,此次使用Ini配置文件初始化SecurityManager */
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("/Users/han/Documents/idea/shiro/src/main/resources/shiro-realm.ini");

        /*得到SecurityManager实例 并绑定给SecurityUtils*/
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        /*得到Subject及创建用户名和密码的身份验证Token */
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");

        try {
            subject.login(token);
            System.out.println("验证通过..");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        subject.logout();
    }
}


/** 笔记
 *首先调用 Subject.login(token) 进行登录，其会自动委托给 Security Manager，调用之前必须通过 SecurityUtils.setSecurityManager() 设置；
 *SecurityManager 负责真正的身份验证逻辑；它会委托给 Authenticator 进行身份验证；
 *Authenticator 才是真正的身份验证者，Shiro API 中核心的身份认证入口点，此处可以自定义插入自己的实现；
 *Authenticator 可能会委托给相应的 AuthenticationStrategy 进行多 Realm 身份验证，默认 ModularRealmAuthenticator 会调用 AuthenticationStrategy 进行多 Realm 身份验证；
 *Authenticator 会把相应的 token 传入 Realm，从 Realm 获取身份验证信息，如果没有返回 / 抛出异常表示身份验证失败了。此处可以配置多个 Realm，将按照相应的顺序及策略进行访问。
 *
 */

