package net.hujian.mybatis;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by hujian on 16-9-5.
 */
public interface CRUDBYNote {
    /**
     * insert by note
     * @param info
     */
    @Insert("insert into info(name,age,school) values(#{name},#{age},#{school})")
    public  void insertInfo(Info info);

    /**
     * delete
     * @param id
     */
    @Delete("delete from info where id=#{id}")
    public void deleteInfo(int id);

    /**
     * update the info
     * @param info
     */
    @Update("update info set name=#{name},age=#{age},school=#{school} where id=#{id}")
    public void updateInfo(Info info);

    /**
     * select one by id
     * @param id
     * @return
     */
    @Select("select * from info where id=#{id}")
    public Info selectOne(int id);

    /**
     * select all
     * @return
     */
    @Select("select * from info")
    public List<Info> selectList();
}
