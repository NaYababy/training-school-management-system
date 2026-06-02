package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

public class CancellationRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    private Long studentId;

    private Long subjectId;

    private Long teacherId;

    @Excel(name = "注销课时数")
    private Integer cancelHours;

    @Excel(name = "注销时间")
    private Date cancelTime;

    private String photoUrl;

    @Excel(name = "备注")
    private String remark;

    @Excel(name = "学生姓名")
    private String studentName;

    @Excel(name = "科目名称")
    private String subjectName;

    @Excel(name = "操作教师")
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

    public Integer getCancelHours() {
        return cancelHours;
    }

    public void setCancelHours(Integer cancelHours) {
        this.cancelHours = cancelHours;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
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
            .append("cancelHours", getCancelHours())
            .append("cancelTime", getCancelTime())
            .append("photoUrl", getPhotoUrl())
            .append("remark", getRemark())
            .toString();
    }
}