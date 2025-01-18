package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.exception.DeptNotEmptyException;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    //部门列表查询
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //删除部门
    @Log
    @DeleteMapping
    public Result delete(Integer id) {

        int empCount = deptService.getEmpCountByDeptId(id);
        if (empCount > 0) {
            throw new DeptNotEmptyException("对不起，当前部门下有员工，不能直接删除~");
        }

        log.info("删除的id部门为：{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    //添加部门
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("新增部门：{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    //根据ID查询
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("ID查询部门：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部门
    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();
    }

}
