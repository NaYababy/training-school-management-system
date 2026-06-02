package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CancellationRecord;

public interface CancellationRecordMapper
{
    CancellationRecord selectCancellationRecordById(Long id);

    List<CancellationRecord> selectCancellationRecordList(CancellationRecord record);

    int insertCancellationRecord(CancellationRecord record);

    int updateCancellationRecord(CancellationRecord record);

    int deleteCancellationRecordById(Long id);

    int deleteCancellationRecordByIds(Long[] ids);

    int countByStudentId(Long studentId);

    int countBySubjectId(Long subjectId);

    int countByTeacherId(Long teacherId);
}