package cn.hanyz.shiro.demo4;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.Arrays;


/**
 * JDBC_REALM
 * @param
 * @author han
 * @date 18/4/12 下午7:51
 * @return
 */
public class Demo4 {

    public static void main(String[] args) {

        /*获取SecurityManagerFactory工厂,此次使用Ini配置文件初始化SecurityManager */
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("/Users/han/Documents/idea/shiro/src/main/resources/shiro-role.ini");

        /*得到SecurityManager实例 并绑定给SecurityUtils*/
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        /*得到Subject及创建用户名和密码的身份验证Token */
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");


        try {
            subject.login(token);
            System.out.println("验证通过..");

            //检查是否有拥有角色
            System.out.println(subject.hasRole("role1"));

            System.out.println(subject.hasAllRoles(Arrays.asList("role1","role2")));

            //判断用户是否拥有权限
            System.out.println(subject.isPermitted("user:create"));

            System.out.println(subject.isPermittedAll("user:create","user:delete"));

        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        subject.logout();
    }
}


/** 笔记
 *
 *
 */

