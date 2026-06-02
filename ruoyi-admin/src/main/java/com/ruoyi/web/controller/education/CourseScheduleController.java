package com.ruoyi.web.controller.education;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.CourseSchedule;
import com.ruoyi.system.service.ICourseScheduleService;

@RestController
@RequestMapping("/education/schedule")
public class CourseScheduleController extends BaseController
{
    @Autowired
    private ICourseScheduleService courseScheduleService;

    @PreAuthorize("@ss.hasPermi('education:schedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(CourseSchedule schedule)
    {
        startPage();
        List<CourseSchedule> list = courseScheduleService.selectCourseScheduleList(schedule);
        return getDataTable(list);
    }

    @Log(title = "课程排课", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('education:schedule:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, CourseSchedule schedule)
    {
        List<CourseSchedule> list = courseScheduleService.selectCourseScheduleList(schedule);
        ExcelUtil<CourseSchedule> util = new ExcelUtil<CourseSchedule>(CourseSchedule.class);
        util.exportExcel(response, list, "课程排课数据");
    }

    @PreAuthorize("@ss.hasPermi('education:schedule:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(courseScheduleService.selectCourseScheduleById(id));
    }

    @PreAuthorize("@ss.hasPermi('education:schedule:add')")
    @Log(title = "课程排课", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CourseSchedule schedule)
    {
        String conflict = courseScheduleService.checkTimeConflict(schedule);
        if (conflict != null) {
            return error(conflict);
        }
        return toAjax(courseScheduleService.insertCourseSchedule(schedule));
    }

    @PreAuthorize("@ss.hasPermi('education:schedule:edit')")
    @Log(title = "课程排课", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CourseSchedule schedule)
    {
        String conflict = courseScheduleService.checkTimeConflict(schedule);
        if (conflict != null) {
            return error(conflict);
        }
        return toAjax(courseScheduleService.updateCourseSchedule(schedule));
    }

    @PreAuthorize("@ss.hasPermi('education:schedule:remove')")
    @Log(title = "课程排课", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        courseScheduleService.deleteCourseScheduleByIds(ids);
        return success();
    }

    @GetMapping("/teacher/{teacherId}")
    public AjaxResult getTeacherSchedule(@PathVariable Long teacherId)
    {
        return success(courseScheduleService.getTeacherSchedule(teacherId));
    }

    @GetMapping("/classroom/{classroomId}")
    public AjaxResult getClassroomSchedule(@PathVariable Long classroomId)
    {
        return success(courseScheduleService.getClassroomSchedule(classroomId));
    }
}