package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.CancellationRecord;
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.domain.Teacher;
import com.ruoyi.system.mapper.CancellationRecordMapper;
import com.ruoyi.system.mapper.ClassroomMapper;
import com.ruoyi.system.mapper.CourseScheduleMapper;
import com.ruoyi.system.mapper.StudentMapper;
import com.ruoyi.system.mapper.StudentSubjectMapper;
import com.ruoyi.system.mapper.SubjectMapper;
import com.ruoyi.system.mapper.TeacherMapper;
import com.ruoyi.system.mapper.TeacherSubjectMapper;
import com.ruoyi.system.service.IEducationStatisticsService;

@Service
public class EducationStatisticsServiceImpl implements IEducationStatisticsService
{
    @Autowired
    private CancellationRecordMapper cancellationRecordMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentSubjectMapper studentSubjectMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherSubjectMapper teacherSubjectMapper;

    @Autowired
    private SubjectMapper subjectMapper;

    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    private CourseScheduleMapper courseScheduleMapper;

    @Override
    public Map<String, Object> getDailyStatistics(String date)
    {
        Map<String, Object> result = new HashMap<>();
        
        CancellationRecord record = new CancellationRecord();
        Map<String, Object> params = new HashMap<>();
        params.put("beginTime", date);
        params.put("endTime", date);
        record.setParams(params);
        
        List<CancellationRecord> records = cancellationRecordMapper.selectCancellationRecordList(record);
        
        int totalHours = records.stream().mapToInt(CancellationRecord::getCancelHours).sum();
        long studentCount = records.stream().map(CancellationRecord::getStudentId).distinct().count();
        
        result.put("totalCancelHours", totalHours);
        result.put("cancelStudentCount", studentCount);
        result.put("recordCount", records.size());
        
        return result;
    }

    @Override
    public Map<String, Object> getWeeklyStatistics(String startDate, String endDate)
    {
        Map<String, Object> result = new HashMap<>();
        
        CancellationRecord record = new CancellationRecord();
        Map<String, Object> params = new HashMap<>();
        params.put("beginTime", startDate);
        params.put("endTime", endDate);
        record.setParams(params);
        
        List<CancellationRecord> records = cancellationRecordMapper.selectCancellationRecordList(record);
        
        int totalHours = records.stream().mapToInt(CancellationRecord::getCancelHours).sum();
        long studentCount = records.stream().map(CancellationRecord::getStudentId).distinct().count();
        
        result.put("totalCancelHours", totalHours);
        result.put("cancelStudentCount", studentCount);
        result.put("recordCount", records.size());
        
        return result;
    }

    @Override
    public Map<String, Object> getPeriodStatistics(String startDate, String endDate)
    {
        Map<String, Object> result = new HashMap<>();
        
        CancellationRecord record = new CancellationRecord();
        Map<String, Object> params = new HashMap<>();
        params.put("beginTime", startDate);
        params.put("endTime", endDate);
        record.setParams(params);
        
        List<CancellationRecord> records = cancellationRecordMapper.selectCancellationRecordList(record);
        
        int totalCancelHours = records.stream().mapToInt(CancellationRecord::getCancelHours).sum();
        long studentCount = records.stream().map(CancellationRecord::getStudentId).distinct().count();
        
        List<Student> students = studentMapper.selectStudentList(new Student());
        long newStudentCount = students.stream()
            .filter(s -> {
                String createTime = s.getCreateTime().toString().split(" ")[0];
                return createTime.compareTo(startDate) >= 0 && createTime.compareTo(endDate) <= 0;
            })
            .count();
        
        BigDecimal totalFee = students.stream()
            .filter(s -> {
                String createTime = s.getCreateTime().toString().split(" ")[0];
                return createTime.compareTo(startDate) >= 0 && createTime.compareTo(endDate) <= 0;
            })
            .map(Student::getTotalFee)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        result.put("totalCancelHours", totalCancelHours);
        result.put("cancelStudentCount", studentCount);
        result.put("newStudentCount", newStudentCount);
        result.put("totalFee", totalFee);
        
        return result;
    }

    @Override
    public Map<String, Object> getSubjectStatistics()
    {
        Map<String, Object> result = new HashMap<>();
        return result;
    }

    @Override
    public Map<String, Object> getTeacherStatistics()
    {
        Map<String, Object> result = new HashMap<>();
        
        List<Teacher> teachers = teacherMapper.selectTeacherList(new Teacher());
        
        for (Teacher teacher : teachers) {
            Map<String, Object> stats = new HashMap<>();
            stats.put("teacherName", teacher.getTeacherName());
            stats.put("commissionRate", teacher.getCommissionRate());
            
            List<?> subjects = teacherSubjectMapper.selectTeacherSubjectByTeacherId(teacher.getId());
            stats.put("subjectCount", subjects.size());
            
            result.put(String.valueOf(teacher.getId()), stats);
        }
        
        return result;
    }

    @Override
    public Map<String, Object> getStudentStatistics()
    {
        Map<String, Object> result = new HashMap<>();
        
        List<Student> students = studentMapper.selectStudentList(new Student());
        
        int totalStudents = students.size();
        int totalHours = students.stream().mapToInt(Student::getTotalHours).sum();
        int remainingHours = students.stream().mapToInt(Student::getRemainingHours).sum();
        int canceledHours = totalHours - remainingHours;
        
        result.put("totalStudents", totalStudents);
        result.put("totalHours", totalHours);
        result.put("remainingHours", remainingHours);
        result.put("canceledHours", canceledHours);
        
        return result;
    }

    @Override
    public Map<String, Object> getDashboardStatistics()
    {
        Map<String, Object> result = new HashMap<>();
        
        int studentCount = studentMapper.selectStudentList(new Student()).size();
        int subjectCount = subjectMapper.selectSubjectList(null).size();
        int teacherCount = teacherMapper.selectTeacherList(new Teacher()).size();
        int classroomCount = classroomMapper.selectClassroomList(null).size();
        
        List<Student> students = studentMapper.selectStudentList(new Student());
        BigDecimal totalFee = students.stream()
            .map(Student::getTotalFee)
            .filter(fee -> fee != null)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        
        result.put("studentCount", studentCount);
        result.put("subjectCount", subjectCount);
        result.put("teacherCount", teacherCount);
        result.put("classroomCount", classroomCount);
        result.put("totalFee", totalFee);
        
        return result;
    }
}