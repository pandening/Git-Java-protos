package net.hujian.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by hujian on 16-9-5.
 */
public class MyBatisUtil {
    /**
     * get the sql session factory
     * @return
     */
    public static SqlSessionFactory getSqlSessionFactory(){
        String resource="net/hujian/xml/config.xml";
        InputStream is=MyBatisUtil.class.getClassLoader().getResourceAsStream(resource);
        return (new SqlSessionFactoryBuilder().build(is));
    }

    /**
     * get a sql session
     * @return
     */
    public static SqlSession getSqlSession(){
        return getSqlSessionFactory().openSession();
    }

    /**
     * true means auto comment.
     * @return
     */
    public  static SqlSession getSqlSessionWithComment(){
        return getSqlSessionFactory().openSession(true);
    }
}
