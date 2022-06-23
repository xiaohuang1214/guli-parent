package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author George
 * @since 2022-06-21
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;
    //1.查询讲师表所有数据
    //rest风格
    @ApiOperation(value = "查询所有讲师")
    @GetMapping("/findAll")
    public R findAllTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        return R.ok().data("item",list);
    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("/{id}")
    public R deleteTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable("id") String id){
        int a=10/0;
        eduTeacherService.removeById(id);
        return R.ok();
    }

    /**
     * @param current 当前页
     * @param limit 每页记录数
     * @return
     */
    @ApiOperation(value = "讲师分页")
    @GetMapping("/pageTeacher/{current}/{limit}")
    public R pageTeacher(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable("current") Long current,
                         @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable("limit") Long limit){
        Page<EduTeacher> eduTeacherPage = new Page<>(current,limit);
        eduTeacherService.page(eduTeacherPage, null);
        //总记录数
        long total = eduTeacherPage.getTotal();
        //数据集合
        List<EduTeacher> records = eduTeacherPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }

    @ApiOperation(value = "条件查询讲师分页")
    @PostMapping("/pageTeacherQuery/{current}/{limit}")
    public R pageTeacherQuery(@ApiParam(name = "current", value = "当前页", required = true) @PathVariable("current") Long current,
                              @ApiParam(name = "limit", value = "每页记录数", required = true) @PathVariable("limit") Long limit,
                              @RequestBody(required = false) EduTeacher eduTeacher){

        Page<EduTeacher> eduTeacherPage = new Page<>(current, limit);
        //查询条件
        LambdaQueryWrapper<EduTeacher> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(eduTeacher.getName() != null,EduTeacher::getName,eduTeacher.getName());
        queryWrapper.eq(eduTeacher.getLevel() != null,EduTeacher::getLevel,eduTeacher.getLevel());
        queryWrapper.ge(eduTeacher.getGmtCreate() != null,EduTeacher::getGmtCreate,eduTeacher.getGmtCreate());
        queryWrapper.le(eduTeacher.getGmtModified() != null,EduTeacher::getGmtModified,eduTeacher.getGmtModified());

        eduTeacherService.page(eduTeacherPage, queryWrapper);
        //总记录数
        long total = eduTeacherPage.getTotal();
        //数据集合
        List<EduTeacher> records = eduTeacherPage.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("rows",records);

        return R.ok().data(map);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("/addEduTeacher")
    public R addEduTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.save(eduTeacher);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "根据id查询讲师")
    @GetMapping("/findTeacherById/{id}")
    public R findTeacherById(@ApiParam(name = "id", value = "讲师ID", required = true) @PathVariable String id) {
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("item",teacher);
    }

    @ApiOperation(value = "修改讲师")
    @PostMapping("/updateEduTeacher")
    public R updateEduTeacher(@RequestBody EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag) {
            return R.ok();
        }else {
            return R.error();
        }
    }

}

