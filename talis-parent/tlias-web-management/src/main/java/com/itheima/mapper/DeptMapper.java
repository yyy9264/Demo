package com.itheima.mapper;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Emp;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    @Select("select id, name, create_time, update_time from dept order by update_time desc;")
    public List<Dept> findAll();
    //根据id 删除部门
    @Delete("delete from dept where id=#{id};")//#{id}会转化成预编译mybatis的参数
    void deleteById(Integer id);
    @Insert("insert into dept(name,create_time,update_time) values(#{name},#{createTime},#{updateTime});")
    void insert(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id=#{id};")
    Dept findById(Integer id);
    @Update("update dept set name=#{name},update_time=#{updateTime} where id=#{id};")
    void update(Dept dept);
    @Select("select * from emp where dept_id=#{id};")
    List<Emp> findStudentsByDeptId(Integer id);
}
