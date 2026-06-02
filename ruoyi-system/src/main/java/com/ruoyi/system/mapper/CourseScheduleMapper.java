package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CourseSchedule;

public interface CourseScheduleMapper
{
    CourseSchedule selectCourseScheduleById(Long id);

    List<CourseSchedule> selectCourseScheduleList(CourseSchedule schedule);

    int insertCourseSchedule(CourseSchedule schedule);

    int updateCourseSchedule(CourseSchedule schedule);

    int deleteCourseScheduleById(Long id);

    int deleteCourseScheduleByIds(Long[] ids);

    List<CourseSchedule> checkTimeConflict(CourseSchedule schedule);

    int countByTeacherId(Long teacherId);

    int countByClassroomId(Long classroomId);
}