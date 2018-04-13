package cn.hanyz.shiro.demo1;

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
public class Demo1 {

    public static void main(String[] args) {

        /*获取SecurityManagerFactory工厂,此次使用Ini配置文件初始化SecurityManager */
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("/Users/han/Documents/idea/shiro/src/main/resources/shiro.ini");

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

/**笔记
 *
 * 首先通过 new IniSecurityManagerFactory 并指定一个 ini 配置文件来创建一个 SecurityManager 工厂；
 * 接着获取 SecurityManager 并绑定到 SecurityUtils，这是一个全局设置，设置一次即可；
 * 通过 SecurityUtils 得到 Subject，其会自动绑定到当前线程；如果在 web 环境在请求结束时需要解除绑定；然后获取身份验证的 Token，如用户名 / 密码；
 * 调用 subject.login 方法进行登录，其会自动委托给 SecurityManager.login 方法进行登录；
 * 如果身份验证失败请捕获 AuthenticationException 或其子类，常见的如： DisabledAccountException（禁用的帐号）、LockedAccountException（锁定的帐号）、UnknownAccountException（错误的帐号）、ExcessiveAttemptsException（登录失败次数过多）、IncorrectCredentialsException （错误的凭证）、ExpiredCredentialsException（过期的凭证）等，具体请查看其继承关系；对于页面的错误消息展示，最好使用如 “用户名 / 密码错误” 而不是 “用户名错误”/“密码错误”，防止一些恶意用户非法扫描帐号库；
 * 最后可以调用 subject.logout 退出，其会自动委托给 SecurityManager.logout 方法退出。
 *
 */
