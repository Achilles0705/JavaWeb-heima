package com.itheima.controller;

import com.itheima.exception.ClazzNotEmptyException;
import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;

    //查询所有班级
    @GetMapping("/list")
    public Result list() {
        log.info("查询全部班级数据");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

    //添加班级
    @PostMapping
    public Result add(@RequestBody Clazz clazz) {
        log.info("新增班级：{}", clazz);
        clazzService.add(clazz);
        return Result.success();
    }

    //班级列表查询
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("Clazz分页查询：{}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    //删除班级
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {

        int studentCount = clazzService.getStudentCountByClazzId(id);
        if (studentCount > 0) {
            throw new ClazzNotEmptyException("对不起, 该班级下有学生, 不能直接删除~");
        }

        log.info("删除的id班级为：{}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    //修改班级
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级：{}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    //根据ID查询
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("ID查询班级：{}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

}
