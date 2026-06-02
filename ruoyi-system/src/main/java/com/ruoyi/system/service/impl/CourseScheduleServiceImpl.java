package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.CourseSchedule;
import com.ruoyi.system.mapper.CourseScheduleMapper;
import com.ruoyi.system.service.ICourseScheduleService;

@Service
public class CourseScheduleServiceImpl implements ICourseScheduleService
{
    @Autowired
    private CourseScheduleMapper courseScheduleMapper;

    @Override
    public CourseSchedule selectCourseScheduleById(Long id)
    {
        return courseScheduleMapper.selectCourseScheduleById(id);
    }

    @Override
    public List<CourseSchedule> selectCourseScheduleList(CourseSchedule schedule)
    {
        return courseScheduleMapper.selectCourseScheduleList(schedule);
    }

    @Override
    public int insertCourseSchedule(CourseSchedule schedule)
    {
        return courseScheduleMapper.insertCourseSchedule(schedule);
    }

    @Override
    public int updateCourseSchedule(CourseSchedule schedule)
    {
        return courseScheduleMapper.updateCourseSchedule(schedule);
    }

    @Override
    public int deleteCourseScheduleById(Long id)
    {
        return courseScheduleMapper.deleteCourseScheduleById(id);
    }

    @Override
    public void deleteCourseScheduleByIds(Long[] ids)
    {
        for (Long id : ids)
        {
            courseScheduleMapper.deleteCourseScheduleById(id);
        }
    }

    @Override
    public String checkTimeConflict(CourseSchedule schedule)
    {
        List<CourseSchedule> conflicts = courseScheduleMapper.checkTimeConflict(schedule);
        if (conflicts.isEmpty()) {
            return null;
        }
        CourseSchedule conflict = conflicts.get(0);
        if (conflict.getTeacherId().equals(schedule.getTeacherId())) {
            return "教师在该时间段已有课程安排";
        }
        if (conflict.getClassroomId().equals(schedule.getClassroomId())) {
            return "教室在该时间段已被占用";
        }
        return "时间冲突";
    }

    @Override
    public List<CourseSchedule> getStudentSchedule(Long studentId)
    {
        return courseScheduleMapper.selectCourseScheduleList(new CourseSchedule());
    }

    @Override
    public List<CourseSchedule> getTeacherSchedule(Long teacherId)
    {
        CourseSchedule schedule = new CourseSchedule();
        schedule.setTeacherId(teacherId);
        return courseScheduleMapper.selectCourseScheduleList(schedule);
    }

    @Override
    public List<CourseSchedule> getClassroomSchedule(Long classroomId)
    {
        CourseSchedule schedule = new CourseSchedule();
        schedule.setClassroomId(classroomId);
        return courseScheduleMapper.selectCourseScheduleList(schedule);
    }
}