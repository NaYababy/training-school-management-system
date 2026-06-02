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
import com.ruoyi.system.domain.CancellationRecord;
import com.ruoyi.system.service.ICancellationRecordService;

@RestController
@RequestMapping("/education/cancellation")
public class CancellationRecordController extends BaseController
{
    @Autowired
    private ICancellationRecordService cancellationRecordService;

    @PreAuthorize("@ss.hasPermi('education:cancellation:list')")
    @GetMapping("/list")
    public TableDataInfo list(CancellationRecord record)
    {
        startPage();
        List<CancellationRecord> list = cancellationRecordService.selectCancellationRecordList(record);
        return getDataTable(list);
    }

    @Log(title = "课时注销", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('education:cancellation:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, CancellationRecord record)
    {
        List<CancellationRecord> list = cancellationRecordService.selectCancellationRecordList(record);
        ExcelUtil<CancellationRecord> util = new ExcelUtil<CancellationRecord>(CancellationRecord.class);
        util.exportExcel(response, list, "注销记录数据");
    }

    @PreAuthorize("@ss.hasPermi('education:cancellation:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable Long id)
    {
        return success(cancellationRecordService.selectCancellationRecordById(id));
    }

    @PreAuthorize("@ss.hasPermi('education:cancellation:add')")
    @Log(title = "课时注销", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CancellationRecord record)
    {
        cancellationRecordService.cancelHours(record.getStudentId(), record.getSubjectId(), 
            record.getTeacherId(), record.getCancelHours(), record.getRemark(), record.getPhotoUrl());
        return success();
    }

    @PreAuthorize("@ss.hasPermi('education:cancellation:edit')")
    @Log(title = "课时注销", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CancellationRecord record)
    {
        return toAjax(cancellationRecordService.updateCancellationRecord(record));
    }

    @PreAuthorize("@ss.hasPermi('education:cancellation:remove')")
    @Log(title = "课时注销", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        cancellationRecordService.deleteCancellationRecordByIds(ids);
        return success();
    }
}