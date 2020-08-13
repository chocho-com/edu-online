package com.chocho.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chocho.eduservice.model.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chocho.eduservice.query.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author chocho
 * @since 2020-05-20
 */
public interface EduTeacherService extends IService<EduTeacher> {
    //分页查询
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery);
}
