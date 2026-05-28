package com.ruoyi.system.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

public class Student extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "学生姓名")
    private String studentName;

    @Excel(name = "年级")
    private String grade;

    @Excel(name = "性别")
    private String gender;

    @Excel(name = "手机号码")
    private String phone;

    @Excel(name = "总课时")
    private Integer totalHours;

    @Excel(name = "剩余课时")
    private Integer remainingHours;

    @Excel(name = "缴费金额")
    private BigDecimal totalFee;

    private List<StudentSubject> subjectList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "学生姓名不能为空")
    @Size(min = 0, max = 100, message = "学生姓名不能超过100个字符")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @NotBlank(message = "年级不能为空")
    @Size(min = 0, max = 50, message = "年级不能超过50个字符")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @NotBlank(message = "性别不能为空")
    @Size(min = 0, max = 10, message = "性别不能超过10个字符")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @NotBlank(message = "手机号码不能为空")
    @Size(min = 0, max = 20, message = "手机号码不能超过20个字符")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public Integer getRemainingHours() {
        return remainingHours;
    }

    public void setRemainingHours(Integer remainingHours) {
        this.remainingHours = remainingHours;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public List<StudentSubject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<StudentSubject> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("studentName", getStudentName())
            .append("grade", getGrade())
            .append("gender", getGender())
            .append("phone", getPhone())
            .append("totalHours", getTotalHours())
            .append("remainingHours", getRemainingHours())
            .append("totalFee", getTotalFee())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}