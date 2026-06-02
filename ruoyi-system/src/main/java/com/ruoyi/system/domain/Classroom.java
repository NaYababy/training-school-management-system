package com.ruoyi.system.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

public class Classroom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;

    @Excel(name = "教室编号")
    private String classroomCode;

    @Excel(name = "教室名称")
    private String classroomName;

    @Excel(name = "楼层")
    private String floor;

    @Excel(name = "容量")
    private Integer capacity;

    @Excel(name = "设施")
    private String facilities;

    @Excel(name = "状态")
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "教室编号不能为空")
    @Size(min = 0, max = 50, message = "教室编号不能超过50个字符")
    public String getClassroomCode() {
        return classroomCode;
    }

    public void setClassroomCode(String classroomCode) {
        this.classroomCode = classroomCode;
    }

    @NotBlank(message = "教室名称不能为空")
    @Size(min = 0, max = 100, message = "教室名称不能超过100个字符")
    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }

    @NotBlank(message = "楼层不能为空")
    @Size(min = 0, max = 20, message = "楼层不能超过20个字符")
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("classroomCode", getClassroomCode())
            .append("classroomName", getClassroomName())
            .append("floor", getFloor())
            .append("capacity", getCapacity())
            .append("facilities", getFacilities())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}