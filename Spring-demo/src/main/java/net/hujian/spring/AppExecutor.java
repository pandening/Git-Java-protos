package net.hujian.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by hujian on 16-9-5.
 */
public class AppExecutor {
    private static void print(String msg){
        System.out.println(msg);
    }
    public static  void main(String[] args){
        //ApplicationContext context=new ClassPathXmlApplicationContext("AppContext.xml");
        //Bean iB=(Bean)context.getBean("IBean");
        //print(iB.toString());
        //ApplicationContext context=new AnnotationConfigApplicationContext(JBean.class);
        //Bean iB=(Bean)context.getBean("JBs");
        //iB.setName("hujian");
        // iB.setOld(23);
        // print(iB.toString());
        //ApplicationContext context=new ClassPathXmlApplicationContext("outConfig.xml");
        //OutHelper op=(OutHelper)context.getBean("OutPut");
        //op.o();
        //ApplicationContext context=new ClassPathXmlApplicationContext("DIInfoA.xml");
        //InfoA info=(InfoA)context.getBean("infoA");
        //print("E-mail:"+info.toString());
        ApplicationContext context=new ClassPathXmlApplicationContext(new String[]{"autoscan.xml"});
        AotoScanA aa=(AotoScanA)context.getBean("AutoA");
        print(aa.toString());
        AotoScanB ab=(AotoScanB)context.getBean("AutoB");
        print(ab.toString());
    }
}
