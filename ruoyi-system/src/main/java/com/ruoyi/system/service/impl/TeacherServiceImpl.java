package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Teacher;
import com.ruoyi.system.domain.TeacherSubject;
import com.ruoyi.system.mapper.TeacherMapper;
import com.ruoyi.system.mapper.TeacherSubjectMapper;
import com.ruoyi.system.service.ITeacherService;

@Service
public class TeacherServiceImpl implements ITeacherService
{
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherSubjectMapper teacherSubjectMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Teacher selectTeacherById(Long id)
    {
        return teacherMapper.selectTeacherById(id);
    }

    @Override
    public Teacher selectTeacherByPhone(String phone)
    {
        return teacherMapper.selectTeacherByPhone(phone);
    }

    @Override
    public List<Teacher> selectTeacherList(Teacher teacher)
    {
        return teacherMapper.selectTeacherList(teacher);
    }

    @Override
    public int insertTeacher(Teacher teacher)
    {
        teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        return teacherMapper.insertTeacher(teacher);
    }

    @Override
    public int updateTeacher(Teacher teacher)
    {
        if (teacher.getPassword() != null && !teacher.getPassword().isEmpty()) {
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
        }
        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public int deleteTeacherById(Long id)
    {
        return teacherMapper.deleteTeacherById(id);
    }

    @Override
    @Transactional
    public void deleteTeacherByIds(Long[] ids)
    {
        for (Long id : ids)
        {
            teacherSubjectMapper.deleteTeacherSubjectByTeacherId(id);
            teacherMapper.deleteTeacherById(id);
        }
    }

    @Override
    public boolean checkPhoneUnique(Teacher teacher)
    {
        Long id = StringUtils.isNull(teacher.getId()) ? -1L : teacher.getId();
        int count = teacherMapper.checkPhoneUnique(teacher.getPhone());
        return count == 0 || (count == 1 && teacherMapper.selectTeacherById(id) != null);
    }

    @Override
    public List<TeacherSubject> selectTeacherSubjectByTeacherId(Long teacherId)
    {
        return teacherSubjectMapper.selectTeacherSubjectByTeacherId(teacherId);
    }

    @Override
    public int insertTeacherSubject(TeacherSubject teacherSubject)
    {
        return teacherSubjectMapper.insertTeacherSubject(teacherSubject);
    }

    @Override
    public int updateTeacherSubject(TeacherSubject teacherSubject)
    {
        return teacherSubjectMapper.updateTeacherSubject(teacherSubject);
    }

    @Override
    public int deleteTeacherSubjectByTeacherId(Long teacherId)
    {
        return teacherSubjectMapper.deleteTeacherSubjectByTeacherId(teacherId);
    }

    @Override
    @Transactional
    public void saveTeacherWithSubjects(Teacher teacher)
    {
        if (teacher.getId() == null)
        {
            teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
            teacherMapper.insertTeacher(teacher);
        }
        else
        {
            if (teacher.getPassword() != null && !teacher.getPassword().isEmpty()) {
                teacher.setPassword(passwordEncoder.encode(teacher.getPassword()));
            }
            teacherMapper.updateTeacher(teacher);
            teacherSubjectMapper.deleteTeacherSubjectByTeacherId(teacher.getId());
        }

        if (teacher.getSubjectList() != null && !teacher.getSubjectList().isEmpty())
        {
            for (TeacherSubject ts : teacher.getSubjectList())
            {
                ts.setTeacherId(teacher.getId());
                if (ts.getIsMain() == null) {
                    ts.setIsMain(0);
                }
                teacherSubjectMapper.insertTeacherSubject(ts);
            }
        }
    }

    @Override
    public List<Teacher> selectAllTeachers()
    {
        return teacherMapper.selectTeacherList(new Teacher());
    }
}