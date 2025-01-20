package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    // 查询所有学生
    List<Student> list(StudentQueryParam studentQueryParam);
    // 保存学生
    void insert(Student student);
    // 根据id查询学生
    @Select("select * from student where id = #{id}")
    Student getById(Integer id);
    // 更新学生
    void update(Student student);
    //批量删除学生
    void deleteById(List<Integer> ids);
    // 统计学生数量
    @MapKey("clazz")
    List<Map<String, Object>> getStudentCountData();
    @MapKey("name")
    List<Map<String, Object>> getStudentDegreeData();
}
