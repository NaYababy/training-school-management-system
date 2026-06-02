package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.StudentSubject;

public interface StudentSubjectMapper
{
    StudentSubject selectStudentSubjectById(Long id);

    List<StudentSubject> selectStudentSubjectList(StudentSubject studentSubject);

    List<StudentSubject> selectStudentSubjectByStudentId(Long studentId);

    int insertStudentSubject(StudentSubject studentSubject);

    int updateStudentSubject(StudentSubject studentSubject);

    int deleteStudentSubjectById(Long id);

    int deleteStudentSubjectByStudentId(Long studentId);

    int deleteStudentSubjectBySubjectId(Long subjectId);

    int updateSubjectRemainingHours(StudentSubject studentSubject);

    int checkUnique(StudentSubject studentSubject);

    StudentSubject selectStudentSubjectByStudentAndSubject(Long studentId, Long subjectId);
}