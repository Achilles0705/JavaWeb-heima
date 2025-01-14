package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    //学员列表查询
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("Student分页查询：{}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    //添加学员
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("新增学员：{}", student);
        studentService.add(student);
        return Result.success();
    }

    //删除学员
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids) {
        log.info("删除的id学员为：{}", ids);
        // 将字符串 ids 转换为 List<Integer>
        List<Integer> idList = Arrays.stream(ids.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        studentService.delete(idList);
        return Result.success();
    }

    //根据ID查询
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("ID查询学员信息：{}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    //修改学员
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员：{}", student);
        studentService.update(student);
        return Result.success();
    }

    //违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id , @PathVariable Integer score){
        studentService.violationHandle(id, score);
        return Result.success();
    }

}
