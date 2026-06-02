package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Student;

public interface StudentMapper
{
    Student selectStudentById(Long id);

    Student selectStudentByPhone(String phone);

    List<Student> selectStudentList(Student student);

    int insertStudent(Student student);

    int updateStudent(Student student);

    int deleteStudentById(Long id);

    int deleteStudentByIds(Long[] ids);

    int checkPhoneUnique(String phone);

    int countStudentBySubjectId(Long subjectId);

    int updateStudentRemainingHours(Long studentId, Integer hours);
}