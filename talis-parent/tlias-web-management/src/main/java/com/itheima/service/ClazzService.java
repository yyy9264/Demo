package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface ClazzService {

    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    void save(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    void deleteById(Integer id);

    List<Clazz> findAll();
}
