package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Teacher;
import com.ruoyi.system.domain.TeacherSubject;

public interface ITeacherService
{
    Teacher selectTeacherById(Long id);

    Teacher selectTeacherByPhone(String phone);

    List<Teacher> selectTeacherList(Teacher teacher);

    int insertTeacher(Teacher teacher);

    int updateTeacher(Teacher teacher);

    int deleteTeacherById(Long id);

    void deleteTeacherByIds(Long[] ids);

    boolean checkPhoneUnique(Teacher teacher);

    List<TeacherSubject> selectTeacherSubjectByTeacherId(Long teacherId);

    int insertTeacherSubject(TeacherSubject teacherSubject);

    int updateTeacherSubject(TeacherSubject teacherSubject);

    int deleteTeacherSubjectByTeacherId(Long teacherId);

    void saveTeacherWithSubjects(Teacher teacher);

    List<Teacher> selectAllTeachers();

    List<Teacher> selectTeachersBySubject(Long subjectId);
}