package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CancellationRecord;

public interface ICancellationRecordService
{
    CancellationRecord selectCancellationRecordById(Long id);

    List<CancellationRecord> selectCancellationRecordList(CancellationRecord record);

    int insertCancellationRecord(CancellationRecord record);

    int updateCancellationRecord(CancellationRecord record);

    int deleteCancellationRecordById(Long id);

    void deleteCancellationRecordByIds(Long[] ids);

    int cancelHours(Long studentId, Long subjectId, Long teacherId, Integer hours, String remark);

    int cancelHours(Long studentId, Long subjectId, Long teacherId, Integer hours, String remark, String photoUrl);
}