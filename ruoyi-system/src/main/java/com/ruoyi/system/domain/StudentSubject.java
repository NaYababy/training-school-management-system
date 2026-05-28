package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

public class StudentSubject extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long studentId;

    private Long subjectId;

    private Long teacherId;

    private Integer subjectHours;

    private Integer subjectRemainingHours;

    private String subjectName;

    private String teacherName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getSubjectHours() {
        return subjectHours;
    }

    public void setSubjectHours(Integer subjectHours) {
        this.subjectHours = subjectHours;
    }

    public Integer getSubjectRemainingHours() {
        return subjectRemainingHours;
    }

    public void setSubjectRemainingHours(Integer subjectRemainingHours) {
        this.subjectRemainingHours = subjectRemainingHours;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("studentId", getStudentId())
            .append("subjectId", getSubjectId())
            .append("teacherId", getTeacherId())
            .append("subjectHours", getSubjectHours())
            .append("subjectRemainingHours", getSubjectRemainingHours())
            .append("createTime", getCreateTime())
            .toString();
    }
}