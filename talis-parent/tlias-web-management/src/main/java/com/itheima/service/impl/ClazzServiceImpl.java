package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.exception.DeleteException;
import com.itheima.mapper.ClazzMapper;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    /*
    分页查询班级信息
     */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        // 执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        //补全基本信息:status
        clazzList.forEach(clazz -> {
            LocalDate now = LocalDate.now();
            if (clazz.getEndDate().isBefore(now)) {
                clazz.setStatus("已结课");
            } else if(clazz.getBeginDate().isAfter(now)) {
                clazz.setStatus("未开班");
            }else{
                clazz.setStatus("在读中");
            }
        });
        // 获取分页信息
        PageInfo<Clazz> pageInfo = new PageInfo<>(clazzList);

        // 返回分页结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }
    /*
    添加班级信息
     */
    @Override
    public void save(Clazz clazz) {
        // 设置创建时间和更新时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }
    /*
    根据id查询班级信息
     */
    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }
    /*
    修改班级信息
     */
    @Override
    public void update(Clazz clazz) {
        // 设置更新时间
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }
    /*
    删除班级信息
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void deleteById(Integer id) {
        //判断该班级是否可以删除
        List<Student> list= clazzMapper.findStudentsByClazzId(id);
        if(list.size()!=0){
            throw new DeleteException("该班级下有学生，不能删除");
        }
        clazzMapper.deleteById(id);
    }
    /*
    查询所有班级信息
     */
    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}
