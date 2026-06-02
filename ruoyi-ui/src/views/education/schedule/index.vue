<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="科目" prop="subjectId">
        <el-select v-model="queryParams.subjectId" placeholder="请选择科目" clearable>
          <el-option v-for="subject in subjectOptions" :key="subject.id" :label="subject.subjectName" :value="subject.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="教师" prop="teacherId">
        <el-select v-model="queryParams.teacherId" placeholder="请选择教师" clearable>
          <el-option v-for="teacher in teacherOptions" :key="teacher.id" :label="teacher.teacherName" :value="teacher.id" />
        </el-select>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option label="正常" value="正常" />
          <el-option label="调课" value="调课" />
          <el-option label="取消" value="取消" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['education:schedule:add']">新增排课</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate" v-hasPermi="['education:schedule:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['education:schedule:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['education:schedule:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scheduleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="科目" align="center" prop="subjectName" :show-overflow-tooltip="true" />
      <el-table-column label="教师" align="center" prop="teacherName" :show-overflow-tooltip="true" />
      <el-table-column label="教室" align="center" prop="classroomName" :show-overflow-tooltip="true" />
      <el-table-column label="上课日期" align="center" prop="classDate" />
      <el-table-column label="开始时间" align="center" prop="startTime" />
      <el-table-column label="结束时间" align="center" prop="endTime" />
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <el-tag :type="scope.row.status === '正常' ? 'success' : scope.row.status === '调课' ? 'warning' : 'danger'">{{ scope.row.status }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)" v-hasPermi="['education:schedule:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['education:schedule:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="科目" prop="subjectId">
          <el-select v-model="form.subjectId" placeholder="请选择科目">
            <el-option v-for="subject in subjectOptions" :key="subject.id" :label="subject.subjectName" :value="subject.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="教师" prop="teacherId">
          <el-select v-model="form.teacherId" placeholder="请选择教师">
            <el-option v-for="teacher in teacherOptions" :key="teacher.id" :label="teacher.teacherName" :value="teacher.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="教室" prop="classroomId">
          <el-select v-model="form.classroomId" placeholder="请选择教室">
            <el-option v-for="classroom in classroomOptions" :key="classroom.id" :label="classroom.classroomName" :value="classroom.id" />
          </el-select>
        </el-form-item>
        <el-form-item label="上课日期" prop="classDate">
          <el-date-picker v-model="form.classDate" type="date" placeholder="请选择日期" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-time-picker v-model="form.startTime" placeholder="请选择开始时间" />
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-time-picker v-model="form.endTime" placeholder="请选择结束时间" />
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option label="正常" value="正常" />
            <el-option label="调课" value="调课" />
            <el-option label="取消" value="取消" />
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listSchedule, getSchedule, addSchedule, updateSchedule, delSchedule } from "@/api/education/schedule"
import { getAllSubjects } from "@/api/education/subject"
import { getAllTeachers } from "@/api/education/teacher"
import { getAllClassrooms } from "@/api/education/classroom"

export default {
  name: "Schedule",
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      scheduleList: [],
      title: "",
      open: false,
      subjectOptions: [],
      teacherOptions: [],
      classroomOptions: [],
      queryParams: { pageNum: 1, pageSize: 10, subjectId: undefined, teacherId: undefined, status: undefined },
      form: { subjectId: null, teacherId: null, classroomId: null, classDate: undefined, startTime: undefined, endTime: undefined, status: '正常', remark: undefined },
      rules: {
        subjectId: [{ required: true, message: "科目不能为空", trigger: "change" }],
        teacherId: [{ required: true, message: "教师不能为空", trigger: "change" }],
        classroomId: [{ required: true, message: "教室不能为空", trigger: "change" }],
        classDate: [{ required: true, message: "上课日期不能为空", trigger: "change" }],
        startTime: [{ required: true, message: "开始时间不能为空", trigger: "change" }],
        endTime: [{ required: true, message: "结束时间不能为空", trigger: "change" }]
      }
    }
  },
  created() {
    this.getList()
    this.loadOptions()
  },
  methods: {
    getList() {
      this.loading = true
      listSchedule(this.queryParams).then(response => {
        this.scheduleList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    loadOptions() {
      getAllSubjects().then(response => { this.subjectOptions = response.data })
      getAllTeachers().then(response => { this.teacherOptions = response.data })
      getAllClassrooms().then(response => { this.classroomOptions = response.data })
    },
    cancel() {
      this.open = false
      this.reset()
    },
    reset() {
      this.form = { subjectId: null, teacherId: null, classroomId: null, classDate: undefined, startTime: undefined, endTime: undefined, status: '正常', remark: undefined }
      this.resetForm("form")
    },
    handleQuery() {
      this.queryParams.pageNum = 1
      this.getList()
    },
    resetQuery() {
      this.resetForm("queryForm")
      this.handleQuery()
    },
    handleAdd() {
      this.reset()
      this.open = true
      this.title = "新增排课"
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getSchedule(id).then(response => {
        this.form = response.data
        this.open = true
        this.title = "修改排课"
      })
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != undefined) {
            updateSchedule(this.form).then(response => {
              if (response.code === 200) {
                this.$modal.msgSuccess("修改成功")
                this.open = false
                this.getList()
              } else {
                this.$modal.msgError(response.msg)
              }
            }).catch(error => {
              this.$modal.msgError(error.message)
            })
          } else {
            addSchedule(this.form).then(response => {
              if (response.code === 200) {
                this.$modal.msgSuccess("新增成功")
                this.open = false
                this.getList()
              } else {
                this.$modal.msgError(response.msg)
              }
            }).catch(error => {
              this.$modal.msgError(error.message)
            })
          }
        }
      })
    },
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除排课编号为"' + ids + '"的数据项？').then(function() {
        return delSchedule(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    handleExport() {
      this.download('education/schedule/export', { ...this.queryParams }, `schedule_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>