package com.chocho.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chocho.eduservice.model.EduTeacher;
import com.chocho.eduservice.mapper.EduTeacherMapper;
import com.chocho.eduservice.query.TeacherQuery;
import com.chocho.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author chocho
 * @since 2020-05-20
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery teacherQuery) {
        //查询条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        //升序
        queryWrapper.orderByAsc("sort");
        //查询全部
        if(teacherQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }
        //条件查询
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        if(!StringUtils.isEmpty(name)){
            //模糊查询
            queryWrapper.like("name", name);
        }
        if(level != null){
            //level = level
            queryWrapper.like("level", level);
        }
        if(!StringUtils.isEmpty(begin)){
            //创建时间 >= 起始时间
            queryWrapper.ge("gmt_create", begin);
        }
        if(!StringUtils.isEmpty(end)){
            //创建时间 <= 结束时间
            queryWrapper.le("gmt_create", end);
        }
        baseMapper.selectPage(pageParam, queryWrapper);
    }
}
