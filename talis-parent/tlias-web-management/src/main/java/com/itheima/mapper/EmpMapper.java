package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParams;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 操作员工信息的Mapper
 */
@Mapper
public interface EmpMapper {

    /**
     * 查询员工信息
     */
//    @Select("select emp.*, dept.name as dept_name from emp left join dept on emp.dept_id = dept.id limit #{start},#{pageSize}")
//    public List<Emp> list(Integer start, Integer pageSize);

    /**
     * 获取符合条件的总记录数
     */
//    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
//    Long count();
//    @Select("select emp.*, dept.name as dept_name from emp left join dept on emp.dept_id = dept.id ")

//    public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate end);
    public List<Emp> list(EmpQueryParams empQueryParams);


    //主键返回,sql语句执行后将主键id返回赋值给emp的id属性
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "value (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    @Select("select * from emp")
    List<Emp> findAll();
    //查询指定部门id下的员工
    @Select("select * from emp where dept_id = #{id}")
    List findByDeptId(Integer id);
    //根据用户名和密码查询员工
    @Select("select id,username,password from emp where username = #{username} and password = #{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
