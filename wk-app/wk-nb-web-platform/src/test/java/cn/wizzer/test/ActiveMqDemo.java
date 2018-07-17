package cn.wizzer.test;

import cn.wizzer.app.sys.modules.services.SysConfigService;
import cn.wizzer.app.web.commons.core.WebPlatformMainLauncher;
import com.alibaba.dubbo.config.annotation.Reference;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.nutz.boot.NbApp;
import org.nutz.boot.test.junit4.NbJUnit4Runner;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;

import javax.jms.*;

/**
 * Created by wizzer on 2018/4/1.
 */
@IocBean(create = "init")
@RunWith(NbJUnit4Runner.class)
public class ActiveMqDemo extends Assert {

    @Inject
    @Reference//先启动SYS模块
    private SysConfigService sysConfigService;

    public void init() {
        System.out.println("hello activeMq");
    }

    @Test
    public void test_service_inject() {
        assertNotNull(sysConfigService);
        System.out.println("sys_config:::"+ Json.toJson(sysConfigService.getAllList()));
    }

    @Test
    public void testProduceMsg() throws Exception{
        //连接工厂
        //使用默认的用户名,密码,路径
        //路径为 tcp://host:61616
         ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        //获取一个连接
        Connection connection = connectionFactory.createConnection();
        //创建一个会话
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        //创建队列或者话题
        Queue queue = session.createQueue("HelloActiveMQ");
        //创建生产者或者消费者
        MessageProducer producer = session.createProducer(queue);
        //发送消息
         for (int i = 0; i < 10; i++) {
                 producer.send(session.createTextMessage("activeMQ,你好!"+i));
            }
        //提交操作
        session.commit();
     }


    @Test
     public void testConsumeMsg() throws Exception{
        // 连接工厂
        // 使用默认用户名、密码、路径
        // 路径 tcp://host:61616
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        // 获取一个连接
        Connection connection = connectionFactory.createConnection();
        //开启连接
        connection.start();
        //建立会话,第一个参数是否开启事务,为true时,最后需要session.conmit()的提交
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        // 创建队列或者话题对象
        Queue queue = session.createQueue("HelloActiveMQ");
        // 创建消费者
        MessageConsumer messageConsumer = session.createConsumer(queue);

        while (true) {
             TextMessage message = (TextMessage) messageConsumer.receive(5000);
             if (message != null) {
                   System.out.println(message.getText());
             } else {
                  break;
             }
        }
     }



    public static NbApp createNbApp() {
        NbApp nb = new NbApp().setMainClass(WebPlatformMainLauncher.class).setPrintProcDoc(false);
        nb.getAppContext().setMainPackage("cn.wizzer");
        return nb;
    }
}
