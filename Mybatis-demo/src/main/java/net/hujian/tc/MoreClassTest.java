package net.hujian.tc;

import net.hujian.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by hujian on 16-9-5.
 */
public class MoreClassTest {
    private static void print(String msg){
        System.out.println(msg);
    }
    public static void main(String[] args){
        /**
         * get a sql session
         */
        SqlSession sqlSession= MyBatisUtil.getSqlSessionWithComment();
        /**
         * the query str
         */
        String query="net.hujian.xml.tcs.getClassInfo";
        MoreClass css=sqlSession.selectOne(query,2);
        sqlSession.close();
        /**
         * show the class
         */
        print(css.toString());
    }
}
