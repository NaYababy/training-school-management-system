package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Subject;
import com.ruoyi.system.mapper.SubjectMapper;
import com.ruoyi.system.service.ISubjectService;

@Service
public class SubjectServiceImpl implements ISubjectService
{
    @Autowired
    private SubjectMapper subjectMapper;

    @Override
    public Subject selectSubjectById(Long id)
    {
        return subjectMapper.selectSubjectById(id);
    }

    @Override
    public Subject selectSubjectByCode(String subjectCode)
    {
        return subjectMapper.selectSubjectByCode(subjectCode);
    }

    @Override
    public List<Subject> selectSubjectList(Subject subject)
    {
        return subjectMapper.selectSubjectList(subject);
    }

    @Override
    public List<Subject> selectAllSubjects()
    {
        return subjectMapper.selectSubjectList(new Subject());
    }

    @Override
    public int insertSubject(Subject subject)
    {
        return subjectMapper.insertSubject(subject);
    }

    @Override
    public int updateSubject(Subject subject)
    {
        return subjectMapper.updateSubject(subject);
    }

    @Override
    public int deleteSubjectById(Long id)
    {
        return subjectMapper.deleteSubjectById(id);
    }

    @Override
    public void deleteSubjectByIds(Long[] ids)
    {
        for (Long id : ids)
        {
//            if (hasRelatedData(id))
//            {
//                Subject subject = selectSubjectById(id);
//                throw new ServiceException("科目【" + subject.getSubjectName() + "】存在关联数据，无法删除");
//            }
            subjectMapper.deleteSubjectById(id);
        }
    }

    @Override
    public boolean checkSubjectCodeUnique(Subject subject)
    {
        Long id = StringUtils.isNull(subject.getId()) ? -1L : subject.getId();
        int count = subjectMapper.checkSubjectCodeUnique(subject.getSubjectCode());
        return count == 0 || (count == 1 && subjectMapper.selectSubjectById(id) != null);
    }

    @Override
    public boolean checkSubjectNameUnique(Subject subject)
    {
        Long id = StringUtils.isNull(subject.getId()) ? -1L : subject.getId();
        int count = subjectMapper.checkSubjectNameUnique(subject.getSubjectName());
        return count == 0 || (count == 1 && subjectMapper.selectSubjectById(id) != null);
    }

    @Override
    public boolean hasRelatedData(Long subjectId)
    {
        int studentCount = subjectMapper.countStudentBySubjectId(subjectId);
        int teacherCount = subjectMapper.countTeacherBySubjectId(subjectId);
        return studentCount > 0 || teacherCount > 0;
    }
}