package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ClazzMapper {
    /*
     * 分页查询
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);
    /*
    新增班级信息
     */
    void insert(Clazz clazz);
    @Select("select * from clazz where id = #{id}")
    Clazz getById(Integer id);
    /*
    修改班级信息
     */

    void update(Clazz clazz);
    /*
    删除班级信息
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    @Select("select * from student where clazz_id = #{id}")
    List<Student> findStudentsByClazzId(Integer id);

    //查询所有班级信息
    @Select("select * from clazz")
    List<Clazz> findAll();
}
