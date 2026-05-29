package com.ruoyi.system.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

public class Teacher extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "教师姓名")
    private String teacherName;

    private String password;

    @Excel(name = "手机号码")
    private String phone;

    @Excel(name = "抽成比例")
    private BigDecimal commissionRate;

    private List<TeacherSubject> subjectList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "教师姓名不能为空")
    @Size(min = 0, max = 100, message = "教师姓名不能超过100个字符")
    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @NotBlank(message = "登录密码不能为空")
    @Size(min = 0, max = 100, message = "登录密码不能超过100个字符")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @NotBlank(message = "手机号码不能为空")
    @Size(min = 0, max = 20, message = "手机号码不能超过20个字符")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    public List<TeacherSubject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<TeacherSubject> subjectList) {
        this.subjectList = subjectList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("teacherName", getTeacherName())
            .append("phone", getPhone())
            .append("commissionRate", getCommissionRate())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}