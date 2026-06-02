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
import com.ruoyi.system.domain.Classroom;
import com.ruoyi.system.service.IClassroomService;

@RestController
@RequestMapping("/education/classroom")
public class ClassroomController extends BaseController
{
    @Autowired
    private IClassroomService classroomService;

    @PreAuthorize("@ss.hasPermi('education:classroom:list')")
    @GetMapping("/list")
    public TableDataInfo list(Classroom classroom)
    {
        startPage();
        List<Classroom> list = classroomService.selectClassroomList(classroom);
        return getDataTable(list);
    }

    @Log(title = "教室管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('education:classroom:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, Classroom classroom)
    {
        List<Classroom> list = classroomService.selectClassroomList(classroom);
        ExcelUtil<Classroom> util = new ExcelUtil<Classroom>(Classroom.class);
        util.exportExcel(response, list, "教室数据");
    }

    @PreAuthorize("@ss.hasPermi('education:classroom:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(classroomService.selectClassroomById(id));
    }

    @PreAuthorize("@ss.hasPermi('education:classroom:add')")
    @Log(title = "教室管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody Classroom classroom)
    {
        if (!classroomService.checkClassroomCodeUnique(classroom))
        {
            return error("新增教室'" + classroom.getClassroomName() + "'失败，教室编号已存在");
        }
        classroom.setCreateBy(getUsername());
        return toAjax(classroomService.insertClassroom(classroom));
    }

    @PreAuthorize("@ss.hasPermi('education:classroom:edit')")
    @Log(title = "教室管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody Classroom classroom)
    {
        if (!classroomService.checkClassroomCodeUnique(classroom))
        {
            return error("修改教室'" + classroom.getClassroomName() + "'失败，教室编号已存在");
        }
        classroom.setUpdateBy(getUsername());
        return toAjax(classroomService.updateClassroom(classroom));
    }

    @PreAuthorize("@ss.hasPermi('education:classroom:remove')")
    @Log(title = "教室管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        classroomService.deleteClassroomByIds(ids);
        return success();
    }

    @GetMapping("/all")
    public AjaxResult getAllClassrooms()
    {
        return success(classroomService.selectAllClassrooms());
    }
}