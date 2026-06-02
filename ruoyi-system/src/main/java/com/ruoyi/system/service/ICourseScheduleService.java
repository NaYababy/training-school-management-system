package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CourseSchedule;

public interface ICourseScheduleService
{
    CourseSchedule selectCourseScheduleById(Long id);

    List<CourseSchedule> selectCourseScheduleList(CourseSchedule schedule);

    int insertCourseSchedule(CourseSchedule schedule);

    int updateCourseSchedule(CourseSchedule schedule);

    int deleteCourseScheduleById(Long id);

    void deleteCourseScheduleByIds(Long[] ids);

    String checkTimeConflict(CourseSchedule schedule);

    List<CourseSchedule> getStudentSchedule(Long studentId);

    List<CourseSchedule> getTeacherSchedule(Long teacherId);

    List<CourseSchedule> getClassroomSchedule(Long classroomId);
}