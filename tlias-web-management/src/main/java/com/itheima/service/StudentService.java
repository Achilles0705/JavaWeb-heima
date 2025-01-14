package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    void add(Student student);

    Student getInfo(Integer id);

    void delete(List<Integer> ids);

    void update(Student student);

    void violationHandle(Integer id, Integer score);
}
