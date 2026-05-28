package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Subject;

public interface SubjectMapper
{
    Subject selectSubjectById(Long id);

    Subject selectSubjectByCode(String subjectCode);

    Subject selectSubjectByName(String subjectName);

    List<Subject> selectSubjectList(Subject subject);

    int insertSubject(Subject subject);

    int updateSubject(Subject subject);

    int deleteSubjectById(Long id);

    int deleteSubjectByIds(Long[] ids);

    int checkSubjectCodeUnique(String subjectCode);

    int checkSubjectNameUnique(String subjectName);

    int countStudentBySubjectId(Long subjectId);

    int countTeacherBySubjectId(Long subjectId);
}