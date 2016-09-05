package net.hujian.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 * Created by hujian on 16-9-5.
 */
public class Runner {
    /**
     * print a message
     * @param msg
     */
    public static void print(String msg){
        System.out.println(msg);
    }

    /**
     * another main
     * @param args
     */
    public static void mainY(String[] args){
        /**
         * get a sql session
         */
        SqlSession sql=MyBatisUtil.getSqlSessionWithComment();
        /**
         * get the note mapper
         */
        CRUDBYNote mapper=sql.getMapper(CRUDBYNote.class);
        /**
         * user info
         */
        Info info=new Info();
        info.setAge(25);
        info.setName("Bob");
        info.setSchool("StU");
        /**
         * insert
         */
        mapper.insertInfo(info);
        /**
         * update
         */
        info=mapper.selectOne(4);
        info.setName("hujian");
        mapper.updateInfo(info);
        /**
         * delete
         */
        mapper.deleteInfo(4);
        /**
         * get all
         */
        for(Info i:mapper.selectList()){
            print(i.toString());
        }
    }
    /**
     * the main
     * @param args
     */
    public static void mains(String[] args){
        /**
         * this is the config resource
         */
        String resource="net/hujian/xml/config.xml";
        /**
         * load the resource from xml file
         */
        InputStream is=Runner.class.getClassLoader().getResourceAsStream(resource);
        /**
         * create the sql session
         */
        SqlSessionFactory sqlSessionFactory=new  SqlSessionFactoryBuilder().build(is);
        /**
         * get a session
         */
        SqlSession session=sqlSessionFactory.openSession();
        /**
         * query string
         */
        String query="net.hujian.xml.mymapper.getInfo";
        /**
         * action
         */
        Info info=session.selectOne(query,2);
        /**
         * show the query result
         */
        print(info.toString());
        /**
         * insert
         */
        info.setName("lisa");
        info.setAge(24);
        info.setSchool("CMU");
        CrudByXMLMapper.insertInfo(info);
        /**
         * update
         */
        info.setId(4);
        info.setName("Lisa.R");
        CrudByXMLMapper.updateInfo(info);
        /**
         * delete
         */
        CrudByXMLMapper.deleteInfo(1);
        /**
         * get all
         */
        for(Info i:CrudByXMLMapper.getAll()){
            print(i.toString());
        }
    }
}
