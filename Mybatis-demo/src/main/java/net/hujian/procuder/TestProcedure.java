package net.hujian.procuder;

import net.hujian.mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hujian on 16-9-5.
 */
public class TestProcedure {
    /**
     * print a message
     * @param msg
     */
    public static void print(String msg){
        System.out.println(msg);
    }
    public static void main(String[] args){
        /**
         * get a session
         */
        SqlSession sqlSession= MyBatisUtil.getSqlSessionWithComment();
        String query="net.hujian.xml.procedure.getUserCount";
        /**
         * the result
         */
        Map<String,Integer> resultMap=new HashMap<String, Integer>();
        resultMap.put("sexID",1);
        resultMap.put("userCount",-1);
        sqlSession.selectOne(query,resultMap);
        sqlSession.close();
        /**
         * the result
         */
        print("result="+resultMap.get("userCount"));
    }
}
