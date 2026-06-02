package com.ruoyi.web.controller.education;

import java.util.List;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
import com.ruoyi.system.domain.Teacher;
import com.ruoyi.system.service.ITeacherService;

@RestController
@RequestMapping("/education/teacher")
public class TeacherController extends BaseController
{
    @Autowired
    private ITeacherService teacherService;

    @PreAuthorize("@ss.hasPermi('education:teacher:list')")
    @GetMapping("/list")
    public TableDataInfo list(Teacher teacher)
    {
        startPage();
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        return getDataTable(list);
    }

    @Log(title = "教师管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('education:teacher:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, Teacher teacher)
    {
        List<Teacher> list = teacherService.selectTeacherList(teacher);
        ExcelUtil<Teacher> util = new ExcelUtil<Teacher>(Teacher.class);
        util.exportExcel(response, list, "教师数据");
    }

    @PreAuthorize("@ss.hasPermi('education:teacher:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        AjaxResult result = success(teacherService.selectTeacherById(id));
        result.put("subjectList", teacherService.selectTeacherSubjectByTeacherId(id));
        return result;
    }

    @PreAuthorize("@ss.hasPermi('education:teacher:add')")
    @Log(title = "教师管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Teacher teacher)
    {
        if (!teacherService.checkPhoneUnique(teacher))
        {
            return error("新增教师'" + teacher.getTeacherName() + "'失败，手机号码已存在");
        }
        teacher.setCreateBy(getUsername());
        teacherService.saveTeacherWithSubjects(teacher);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('education:teacher:edit')")
    @Log(title = "教师管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Teacher teacher)
    {
        if (!teacherService.checkPhoneUnique(teacher))
        {
            return error("修改教师'" + teacher.getTeacherName() + "'失败，手机号码已存在");
        }
        teacher.setUpdateBy(getUsername());
        teacherService.saveTeacherWithSubjects(teacher);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('education:teacher:remove')")
    @Log(title = "教师管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        teacherService.deleteTeacherByIds(ids);
        return success();
    }

    @GetMapping("/subject/{teacherId}")
    public AjaxResult getSubjectsByTeacherId(@PathVariable Long teacherId)
    {
        return success(teacherService.selectTeacherSubjectByTeacherId(teacherId));
    }

    @GetMapping("/all")
    public AjaxResult getAllTeachers()
    {
        return success(teacherService.selectAllTeachers());
    }

    @GetMapping("/bySubject/{subjectId}")
    public AjaxResult getTeachersBySubject(@PathVariable Long subjectId)
    {
        return success(teacherService.selectTeachersBySubject(subjectId));
    }
}