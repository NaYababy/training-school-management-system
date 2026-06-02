package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Teacher;

public interface TeacherMapper
{
    Teacher selectTeacherById(Long id);

    Teacher selectTeacherByPhone(String phone);

    List<Teacher> selectTeacherList(Teacher teacher);

    int insertTeacher(Teacher teacher);

    int updateTeacher(Teacher teacher);

    int deleteTeacherById(Long id);

    int deleteTeacherByIds(Long[] ids);

    int checkPhoneUnique(String phone);

    List<Teacher> selectTeachersBySubject(Long subjectId);
}