package net.hujian.mybatis;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by hujian on 16-9-5.
 */
public class CrudByXMLMapper {
    /**
     * insert an item
     * @param info
     */
    public static void insertInfo(Info info){
        /**
         * get a sql session
         */
        SqlSession sql=MyBatisUtil.getSqlSessionWithComment();
        /**
         * the operator string
         */
        String op="net.hujian.xml.mymapper.insertInfo";
        sql.insert(op,info);
        /**
         * close the session
         */
        sql.close();
    }

    /**
     * update the info
     * @param info
     */
    public static void updateInfo(Info info){
        SqlSession sql=MyBatisUtil.getSqlSessionWithComment();
        String op="net.hujian.xml.mymapper.updateInfo";
        sql.update(op,info);
        sql.close();
    }

    /**
     * delete info from database according to the id
     * @param id
     */
    public static void deleteInfo(int id){
        SqlSession sql=MyBatisUtil.getSqlSessionWithComment();
        String op="net.hujian.xml.mymapper.deleteInfo";
        sql.delete(op,id);
        sql.close();
    }

    /**
     * get all infos
     */
    public static List<Info> getAll(){
        SqlSession sql=MyBatisUtil.getSqlSessionWithComment();
        String op="net.hujian.xml.mymapper.getAll";
        List<Info> result=sql.selectList(op);
        sql.close();
        return result;
    }
}
