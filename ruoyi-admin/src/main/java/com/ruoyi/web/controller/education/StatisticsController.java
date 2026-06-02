package com.ruoyi.web.controller.education;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.service.IEducationStatisticsService;

@RestController
@RequestMapping("/education/statistics")
public class StatisticsController extends BaseController
{
    @Autowired
    private IEducationStatisticsService educationStatisticsService;

    @PreAuthorize("@ss.hasPermi('education:statistics:query')")
    @GetMapping("/daily")
    public AjaxResult getDailyStatistics(@RequestParam String date)
    {
        return success(educationStatisticsService.getDailyStatistics(date));
    }

    @PreAuthorize("@ss.hasPermi('education:statistics:query')")
    @GetMapping("/weekly")
    public AjaxResult getWeeklyStatistics(@RequestParam String startDate, @RequestParam String endDate)
    {
        return success(educationStatisticsService.getWeeklyStatistics(startDate, endDate));
    }

    @PreAuthorize("@ss.hasPermi('education:statistics:query')")
    @GetMapping("/period")
    public AjaxResult getPeriodStatistics(@RequestParam String startDate, @RequestParam String endDate)
    {
        return success(educationStatisticsService.getPeriodStatistics(startDate, endDate));
    }

    @PreAuthorize("@ss.hasPermi('education:statistics:query')")
    @GetMapping("/subject")
    public AjaxResult getSubjectStatistics()
    {
        return success(educationStatisticsService.getSubjectStatistics());
    }

    @PreAuthorize("@ss.hasPermi('education:statistics:query')")
    @GetMapping("/teacher")
    public AjaxResult getTeacherStatistics()
    {
        return success(educationStatisticsService.getTeacherStatistics());
    }

    @PreAuthorize("@ss.hasPermi('education:statistics:query')")
    @GetMapping("/student")
    public AjaxResult getStudentStatistics()
    {
        return success(educationStatisticsService.getStudentStatistics());
    }

    @PreAuthorize("@ss.hasPermi('education:statistics:query')")
    @GetMapping("/overview")
    public AjaxResult getOverview()
    {
        return success(educationStatisticsService.getStudentStatistics());
    }
}