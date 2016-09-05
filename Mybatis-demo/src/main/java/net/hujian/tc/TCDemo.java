package net.hujian.tc;

import net.hujian.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

/**
 * Created by hujian on 16-9-5.
 */
public class TCDemo {
    /**
     *
     * @param msg
     */
    private static void print(String msg){
       System.out.println(msg);
    }
    public static void main(String[] args){
        /**
         * get a sql session
         */
        SqlSession sql= MyBatisUtil.getSqlSessionWithComment();
        /**
         * the query str
         */
        String query="net.hujian.xml.tc.getClassInfo";
        /**
         * query
         */
        Class cs=sql.selectOne(query,1);
        sql.close();
        /**
         * show it
         */
        print(cs.toString());
    }
}
