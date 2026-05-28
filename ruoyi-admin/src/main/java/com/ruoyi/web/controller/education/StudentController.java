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
import com.ruoyi.system.domain.Student;
import com.ruoyi.system.domain.StudentSubject;
import com.ruoyi.system.service.IStudentService;

@RestController
@RequestMapping("/education/student")
public class StudentController extends BaseController
{
    @Autowired
    private IStudentService studentService;

    @PreAuthorize("@ss.hasPermi('education:student:list')")
    @GetMapping("/list")
    public TableDataInfo list(Student student)
    {
        startPage();
        List<Student> list = studentService.selectStudentList(student);
        return getDataTable(list);
    }

    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('education:student:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, Student student)
    {
        List<Student> list = studentService.selectStudentList(student);
        ExcelUtil<Student> util = new ExcelUtil<Student>(Student.class);
        util.exportExcel(response, list, "学生数据");
    }

    @PreAuthorize("@ss.hasPermi('education:student:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        AjaxResult result = success(studentService.selectStudentById(id));
        result.put("subjectList", studentService.selectStudentSubjectByStudentId(id));
        return result;
    }

    @PreAuthorize("@ss.hasPermi('education:student:add')")
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Student student)
    {
        if (!studentService.checkPhoneUnique(student))
        {
            return error("新增学生'" + student.getStudentName() + "'失败，手机号码已存在");
        }
        student.setCreateBy(getUsername());
        studentService.saveStudentWithSubjects(student);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('education:student:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Student student)
    {
        if (!studentService.checkPhoneUnique(student))
        {
            return error("修改学生'" + student.getStudentName() + "'失败，手机号码已存在");
        }
        student.setUpdateBy(getUsername());
        studentService.saveStudentWithSubjects(student);
        return success();
    }

    @PreAuthorize("@ss.hasPermi('education:student:remove')")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        studentService.deleteStudentByIds(ids);
        return success();
    }

    @GetMapping("/subject/{studentId}")
    public AjaxResult getSubjectsByStudentId(@PathVariable Long studentId)
    {
        return success(studentService.selectStudentSubjectByStudentId(studentId));
    }

    @GetMapping("/all")
    public AjaxResult getAllStudents()
    {
        return success(studentService.selectStudentList(new Student()));
    }
}