package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Subject;

public interface ISubjectService
{
    Subject selectSubjectById(Long id);

    Subject selectSubjectByCode(String subjectCode);

    List<Subject> selectSubjectList(Subject subject);

    int insertSubject(Subject subject);

    int updateSubject(Subject subject);

    int deleteSubjectById(Long id);

    void deleteSubjectByIds(Long[] ids);

    boolean checkSubjectCodeUnique(Subject subject);

    boolean checkSubjectNameUnique(Subject subject);

    boolean hasRelatedData(Long subjectId);

    List<Subject> selectAllSubjects();
}