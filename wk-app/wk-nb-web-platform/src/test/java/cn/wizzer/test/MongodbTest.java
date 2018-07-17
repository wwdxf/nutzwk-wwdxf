package cn.wizzer.test;

import cn.wizzer.app.test.modules.services.ExampleLogService;
import cn.wizzer.app.web.commons.core.WebPlatformMainLauncher;
import cn.wizzer.app.wwdxf.modules.models.Example_log;
import com.alibaba.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.lang.Times;

import java.util.List;

@IocBean(create = "init")
@RunWith(NbJUnit4Runner.class)
public class MongodbTest {

    @Inject
    @Reference
    private ExampleLogService devDeviceLogService;

    public void init() {
        System.out.println("test-mongo-db");
    }

    @Test
    public void test_mongo_insert() {
        for(int i =0 ;i<45;i++) {
            Example_log log = new Example_log();
            log.setName("ceshitest"+i);
            log.setNote("记录mongo日志test"+i);
            log.setCrateAt(Times.getTS());
            log.setLogType("2");
            devDeviceLogService.insert(log);
            System.out.println("insert:::::"+Json.toJson(log));
        }
    }

    @Test
    public void test_query(){
        Example_log log = new Example_log();
        log.setName("ceshi1");
        List<Object> loglist =  devDeviceLogService.query_many(log);
        System.out.println("loglist:::::"+Json.toJson(loglist));
    }


    @Test
    public void test_update(){
        devDeviceLogService.update_one();
    }

    @Test
    public void test_remove(){
        devDeviceLogService.remove();
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
