package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.Subject;
import com.ruoyi.system.service.ISubjectService;

@RestController
@RequestMapping("/system/subject")
public class SubjectController extends BaseController
{
    @Autowired
    private ISubjectService subjectService;

    @PreAuthorize("@ss.hasPermi('system:subject:list')")
    @GetMapping("/list")
    public TableDataInfo list(Subject subject)
    {
        startPage();
        List<Subject> list = subjectService.selectSubjectList(subject);
        return getDataTable(list);
    }

    @Log(title = "科目管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:subject:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, Subject subject)
    {
        List<Subject> list = subjectService.selectSubjectList(subject);
        ExcelUtil<Subject> util = new ExcelUtil<Subject>(Subject.class);
        util.exportExcel(response, list, "科目数据");
    }

    @PreAuthorize("@ss.hasPermi('system:subject:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(subjectService.selectSubjectById(id));
    }

    @PreAuthorize("@ss.hasPermi('system:subject:add')")
    @Log(title = "科目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Subject subject)
    {
        if (!subjectService.checkSubjectCodeUnique(subject))
        {
            return error("新增科目'" + subject.getSubjectName() + "'失败，科目编码已存在");
        }
        if (!subjectService.checkSubjectNameUnique(subject))
        {
            return error("新增科目'" + subject.getSubjectName() + "'失败，科目名称已存在");
        }
        subject.setCreateBy(getUsername());
        return toAjax(subjectService.insertSubject(subject));
    }

    @PreAuthorize("@ss.hasPermi('system:subject:edit')")
    @Log(title = "科目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Subject subject)
    {
        if (!subjectService.checkSubjectCodeUnique(subject))
        {
            return error("修改科目'" + subject.getSubjectName() + "'失败，科目编码已存在");
        }
        if (!subjectService.checkSubjectNameUnique(subject))
        {
            return error("修改科目'" + subject.getSubjectName() + "'失败，科目名称已存在");
        }
        subject.setUpdateBy(getUsername());
        return toAjax(subjectService.updateSubject(subject));
    }

    @PreAuthorize("@ss.hasPermi('system:subject:remove')")
    @Log(title = "科目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        subjectService.deleteSubjectByIds(ids);
        return success();
    }

    @GetMapping("/all")
    public AjaxResult getAllSubjects()
    {
        return success(subjectService.selectAllSubjects());
    }
}