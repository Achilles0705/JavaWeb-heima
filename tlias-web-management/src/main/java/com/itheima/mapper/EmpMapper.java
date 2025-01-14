package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")//获取到生成的主键-来自mybatis
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}, #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);

    @Select("select id, username, password, name, gender, image, job, salary, entry_date, dept_id, create_time, update_time from emp")
    List<Clazz> findAll();

    //根据ID批量删除员工基本信息
    void deleteByIds(List<Integer> ids);

    //根据ID查询员工信息以及工作经历信息
    Emp getById(Integer id);

    //根据ID更新员工基本信息
    void updateById(Emp emp);

    //统计员工职位人数
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    //统计员工性别人数
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();
}
