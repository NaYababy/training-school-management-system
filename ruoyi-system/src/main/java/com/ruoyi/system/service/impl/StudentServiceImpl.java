package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.domain.StudentSubject;
import com.ruoyi.system.mapper.StudentMapper;
import com.ruoyi.system.mapper.StudentSubjectMapper;
import com.ruoyi.system.service.IStudentService;

@Service
public class StudentServiceImpl implements IStudentService
{
    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentSubjectMapper studentSubjectMapper;

    @Override
    public Student selectStudentById(Long id)
    {
        return studentMapper.selectStudentById(id);
    }

    @Override
    public Student selectStudentByPhone(String phone)
    {
        return studentMapper.selectStudentByPhone(phone);
    }

    @Override
    public List<Student> selectStudentList(Student student)
    {
        return studentMapper.selectStudentList(student);
    }

    @Override
    public int insertStudent(Student student)
    {
        return studentMapper.insertStudent(student);
    }

    @Override
    public int updateStudent(Student student)
    {
        return studentMapper.updateStudent(student);
    }

    @Override
    public int deleteStudentById(Long id)
    {
        return studentMapper.deleteStudentById(id);
    }

    @Override
    @Transactional
    public void deleteStudentByIds(Long[] ids)
    {
        for (Long id : ids)
        {
            studentSubjectMapper.deleteStudentSubjectByStudentId(id);
            studentMapper.deleteStudentById(id);
        }
    }

    @Override
    public boolean checkPhoneUnique(Student student)
    {
        Long id = StringUtils.isNull(student.getId()) ? -1L : student.getId();
        int count = studentMapper.checkPhoneUnique(student.getPhone());
        return count == 0 || (count == 1 && studentMapper.selectStudentById(id) != null);
    }

    @Override
    public List<StudentSubject> selectStudentSubjectByStudentId(Long studentId)
    {
        return studentSubjectMapper.selectStudentSubjectByStudentId(studentId);
    }

    @Override
    public int insertStudentSubject(StudentSubject studentSubject)
    {
        return studentSubjectMapper.insertStudentSubject(studentSubject);
    }

    @Override
    public int updateStudentSubject(StudentSubject studentSubject)
    {
        return studentSubjectMapper.updateStudentSubject(studentSubject);
    }

    @Override
    public int deleteStudentSubjectByStudentId(Long studentId)
    {
        return studentSubjectMapper.deleteStudentSubjectByStudentId(studentId);
    }

    @Override
    @Transactional
    public void saveStudentWithSubjects(Student student)
    {
        if (student.getId() == null)
        {
            studentMapper.insertStudent(student);
        }
        else
        {
            studentMapper.updateStudent(student);
            studentSubjectMapper.deleteStudentSubjectByStudentId(student.getId());
        }

        if (student.getSubjectList() != null && !student.getSubjectList().isEmpty())
        {
            int totalHours = 0;
            int remainingHours = 0;
            
            for (StudentSubject ss : student.getSubjectList())
            {
                ss.setStudentId(student.getId());
                if (ss.getSubjectRemainingHours() == null) {
                    ss.setSubjectRemainingHours(ss.getSubjectHours());
                }
                studentSubjectMapper.insertStudentSubject(ss);
                totalHours += ss.getSubjectHours();
                remainingHours += ss.getSubjectRemainingHours();
            }
            
            student.setTotalHours(totalHours);
            student.setRemainingHours(remainingHours);
            studentMapper.updateStudent(student);
        }
    }
}