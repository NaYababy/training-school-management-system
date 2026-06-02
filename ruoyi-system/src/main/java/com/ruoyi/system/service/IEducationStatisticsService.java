package com.ruoyi.system.service;

import java.util.Map;

public interface IEducationStatisticsService
{
    Map<String, Object> getDailyStatistics(String date);

    Map<String, Object> getWeeklyStatistics(String startDate, String endDate);

    Map<String, Object> getPeriodStatistics(String startDate, String endDate);

    Map<String, Object> getSubjectStatistics();

    Map<String, Object> getTeacherStatistics();

    Map<String, Object> getStudentStatistics();

    Map<String, Object> getDashboardStatistics();
}