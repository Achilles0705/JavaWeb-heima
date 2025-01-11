package com.itheima.service;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface EmpService {
//    PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender,
//                         LocalDate begin, LocalDate end);

    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    List<Clazz> findAll();
}
