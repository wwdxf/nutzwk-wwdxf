package cn.wizzer.test;

import cn.wizzer.app.sys.modules.services.SysConfigService;
import cn.wizzer.app.utils.DateUtil;
import cn.wizzer.app.web.commons.core.WebPlatformMainLauncher;
import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Times;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wizzer on 2018/4/1.
 */
@IocBean(create = "init")
@RunWith(NbJUnit4Runner.class)
public class Demo extends Assert {


    public void init() {
        System.out.println("say hi");
    }

    @Test
    public void test_service_inject() {
        long startAt = Times.getTS();
        System.out.println("startAt="+startAt);
        long endAt = 100*24*60*60L;
        System.out.println("endAt="+endAt);
        long time = (startAt  - endAt)*1000;
        System.out.println("times="+time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String tableName = "sms_send_"+sdf.format(time);
        System.out.println("tableName="+tableName);
    }


    @Test
    public void test_pattrn() {
        String regex = "\\$\\{(.+?)\\}";
        String str = "您正在申请手机注册，验证码为：${code}，5分钟内有!";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if(matcher.find()){
            String key = matcher.group(1);// 键名
            System.out.println("key="+key);
        }
        System.out.println("key=dsdsfsdf");
    }


    // 测试类可提供public的static的createNbApp方法,用于定制当前测试类所需要的NbApp对象.
    // 测试类带@IocBean或不带@IocBean,本规则一样生效
    // 若不提供,默认使用当前测试类作为MainLauncher.
    // 也可以自定义NbJUnit4Runner, 继承NbJUnit4Runner并覆盖其createNbApp方法
    public static NbApp createNbApp() {
        NbApp nb = new NbApp().setMainClass(WebPlatformMainLauncher.class).setPrintProcDoc(false);
        nb.getAppContext().setMainPackage("cn.wizzer");
        return nb;
    }
}
