package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.TeacherSubject;

public interface TeacherSubjectMapper
{
    TeacherSubject selectTeacherSubjectById(Long id);

    List<TeacherSubject> selectTeacherSubjectList(TeacherSubject teacherSubject);

    List<TeacherSubject> selectTeacherSubjectByTeacherId(Long teacherId);

    int insertTeacherSubject(TeacherSubject teacherSubject);

    int updateTeacherSubject(TeacherSubject teacherSubject);

    int deleteTeacherSubjectById(Long id);

    int deleteTeacherSubjectByTeacherId(Long teacherId);

    int deleteTeacherSubjectBySubjectId(Long subjectId);

    int checkUnique(TeacherSubject teacherSubject);
}