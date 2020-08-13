package com.chocho.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chocho.common.R;
import com.chocho.common.ResultCode;
import com.chocho.eduservice.model.EduTeacher;
import com.chocho.eduservice.query.TeacherQuery;
import com.chocho.eduservice.service.EduTeacherService;
import com.chocho.eduservice.handler.ChoChoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author chocho
 * @since 2020-05-20
 */
@RestController
@Api(description="讲师管理")
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "新增讲师")
    @PostMapping
    public R save(
                    @ApiParam(name = "teacher", value = "讲师", required = true)
                    @RequestBody EduTeacher teacher){
        //检查重复主键
        EduTeacher t = teacherService.getById(teacher.getId());
        if(t == null) {
            if(teacherService.save(teacher)){
                return R.ok();
            }else {
                return R.error().message("添加失败");
            }
        }else {
            return R.error().message("主键重复");
        }
    }

    @ApiOperation(value = "逻辑删除教师")
    @DeleteMapping("{id}")
    public R deleteTeacher(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable("id")String id){
        boolean b = teacherService.removeById(id);
        return R.ok();
    }

    @ApiOperation(value = "根据ID修改教师信息")
    @PutMapping("{id}")
    public R updateById(
                        @ApiParam(name = "id", value = "教师ID", required = true)
                        @PathVariable("id")String id,
                        @ApiParam(name = "teacher", value = "教师信息", required = true)
                        @RequestBody EduTeacher teacher){
        teacher.setId(id);
        if(teacherService.updateById(teacher)) {
            return R.ok();
        }else {
            return R.error();
        }
    }

    @ApiOperation(value = "查询所有教师")
    @GetMapping()
    public R getAllTeacherList(){
        List<EduTeacher> list = teacherService.list(null);

        return R.ok().data("list", list);
    }

    @ApiOperation(value = "分页教师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(
                        @ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
                        @RequestBody(required = false) TeacherQuery teacherQuery){

        if(page <= 0 || limit <= 0){
            throw new ChoChoException(ResultCode.PARAM_ERROR, "参数不正确");
        }
        //分页信息
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        //分页查询
        teacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return  R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "根据ID查询讲师")
    @GetMapping("{id}")
    public R getById(
                        @ApiParam(name = "id", value = "讲师ID", required = true)
                        @PathVariable String id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("teacher", teacher);
    }
}

