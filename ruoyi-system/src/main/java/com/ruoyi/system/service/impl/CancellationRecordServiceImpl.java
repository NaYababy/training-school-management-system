package com.ruoyi.system.service.impl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.system.domain.CancellationRecord;
import com.ruoyi.system.domain.StudentSubject;
import com.ruoyi.system.mapper.CancellationRecordMapper;
import com.ruoyi.system.mapper.StudentMapper;
import com.ruoyi.system.mapper.StudentSubjectMapper;
import com.ruoyi.system.service.ICancellationRecordService;

@Service
public class CancellationRecordServiceImpl implements ICancellationRecordService
{
    @Autowired
    private CancellationRecordMapper cancellationRecordMapper;

    @Autowired
    private StudentSubjectMapper studentSubjectMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public CancellationRecord selectCancellationRecordById(Long id)
    {
        return cancellationRecordMapper.selectCancellationRecordById(id);
    }

    @Override
    public List<CancellationRecord> selectCancellationRecordList(CancellationRecord record)
    {
        return cancellationRecordMapper.selectCancellationRecordList(record);
    }

    @Override
    public int insertCancellationRecord(CancellationRecord record)
    {
        return cancellationRecordMapper.insertCancellationRecord(record);
    }

    @Override
    public int updateCancellationRecord(CancellationRecord record)
    {
        return cancellationRecordMapper.updateCancellationRecord(record);
    }

    @Override
    public int deleteCancellationRecordById(Long id)
    {
        return cancellationRecordMapper.deleteCancellationRecordById(id);
    }

    @Override
    public void deleteCancellationRecordByIds(Long[] ids)
    {
        for (Long id : ids)
        {
            cancellationRecordMapper.deleteCancellationRecordById(id);
        }
    }

    @Override
    @Transactional
    public int cancelHours(Long studentId, Long subjectId, Long teacherId, Integer hours, String remark)
    {
        return cancelHours(studentId, subjectId, teacherId, hours, remark, null);
    }

    @Override
    @Transactional
    public int cancelHours(Long studentId, Long subjectId, Long teacherId, Integer hours, String remark, String photoUrl)
    {
        StudentSubject ss = studentSubjectMapper.selectStudentSubjectByStudentAndSubject(studentId, subjectId);
        if (ss == null) {
            throw new ServiceException("学生未报名该科目");
        }
        if (ss.getSubjectRemainingHours() < hours) {
            throw new ServiceException("剩余课时不足");
        }
        
        ss.setSubjectRemainingHours(ss.getSubjectRemainingHours() - hours);
        studentSubjectMapper.updateStudentSubject(ss);
        
        studentMapper.updateStudentRemainingHours(studentId, -hours);
        
        CancellationRecord record = new CancellationRecord();
        record.setStudentId(studentId);
        record.setSubjectId(subjectId);
        record.setTeacherId(teacherId);
        record.setCancelHours(hours);
        record.setCancelTime(new Date());
        record.setRemark(remark);
        record.setPhotoUrl(photoUrl);
        
        return cancellationRecordMapper.insertCancellationRecord(record);
    }
}