package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {

    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time)" +
            "values(#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);

    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz")
    List<Clazz> findAll();

    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    void update(Clazz clazz);

    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id = #{id}")
    Clazz getById(Integer id);

    int getStudentCountByClazzId(Integer id);
}
