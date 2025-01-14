package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> list(StudentQueryParam studentQueryParam);

    void insert(Student student);

    void deleteByIds(List<Integer> ids);

    Student getById(Integer id);

    void updateById(Student student);

    void updateViolation(Integer id, Integer score);

    @MapKey("name")
    List<Map<String, Object>> countStudentDegreeData();

    @MapKey("name")
    List<Map<String, Object>> getStudentCount();
}
