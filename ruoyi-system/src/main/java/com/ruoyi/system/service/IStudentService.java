package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.domain.StudentSubject;

public interface IStudentService
{
    Student selectStudentById(Long id);

    Student selectStudentByPhone(String phone);

    List<Student> selectStudentList(Student student);

    int insertStudent(Student student);

    int updateStudent(Student student);

    int deleteStudentById(Long id);

    void deleteStudentByIds(Long[] ids);

    boolean checkPhoneUnique(Student student);

    List<StudentSubject> selectStudentSubjectByStudentId(Long studentId);

    int insertStudentSubject(StudentSubject studentSubject);

    int updateStudentSubject(StudentSubject studentSubject);

    int deleteStudentSubjectByStudentId(Long studentId);

    void saveStudentWithSubjects(Student student);
}