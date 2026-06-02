<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学生姓名" prop="studentName">
        <el-input v-model="queryParams.studentName" placeholder="请输入学生姓名" clearable style="width: 240px" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="科目" prop="subjectName">
        <el-input v-model="queryParams.subjectName" placeholder="请输入科目名称" clearable style="width: 240px" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="教师" prop="teacherName">
        <el-input v-model="queryParams.teacherName" placeholder="请输入教师姓名" clearable style="width: 240px" @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['education:cancellation:add']">课时注销</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete" v-hasPermi="['education:cancellation:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport" v-hasPermi="['education:cancellation:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="cancellationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学生姓名" align="center" prop="studentName" :show-overflow-tooltip="true" />
      <el-table-column label="科目" align="center" prop="subjectName" :show-overflow-tooltip="true" />
      <el-table-column label="操作教师" align="center" prop="teacherName" :show-overflow-tooltip="true" />
      <el-table-column label="注销课时" align="center" prop="cancelHours" />
      <el-table-column label="注销时间" align="center" prop="cancelTime" width="180">
        <template slot-scope="scope"><span>{{ parseTime(scope.row.cancelTime) }}</span></template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" :show-overflow-tooltip="true" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)" v-hasPermi="['education:cancellation:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize" @pagination="getList" />

    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生" prop="studentId">
          <el-select v-model="form.studentId" placeholder="请选择学生">
            <el-option v-for="student in studentOptions" :key="student.id" :label="student.studentName" :value="student.id" />
          </el-select>
        </el-form-item>
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
        <el-form-item label="注销课时" prop="cancelHours">
          <el-input-number v-model="form.cancelHours" :min="1" />
        </el-form-item>
        <el-form-item label="注销拍照" prop="photoUrl">
          <el-upload
            class="upload-demo"
            action="/common/upload"
            :on-success="handlePhotoUpload"
            :before-upload="beforePhotoUpload"
            :file-list="photoList"
            :limit="1"
            accept="image/jpeg,image/png"
          >
            <el-button size="small" type="primary">点击上传照片</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过2MB</div>
          </el-upload>
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
import { listCancellation, addCancellation, delCancellation } from "@/api/education/cancellation"
import { getAllStudents } from "@/api/education/student"
import { getAllSubjects } from "@/api/education/subject"
import { getAllTeachers } from "@/api/education/teacher"

export default {
  name: "Cancellation",
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      cancellationList: [],
      title: "",
      open: false,
      studentOptions: [],
      subjectOptions: [],
      teacherOptions: [],
      queryParams: { pageNum: 1, pageSize: 10, studentName: undefined, subjectName: undefined, teacherName: undefined },
      form: { studentId: null, subjectId: null, teacherId: null, cancelHours: 1, remark: undefined, photoUrl: undefined },
      photoList: [],
      rules: {
        studentId: [{ required: true, message: "学生不能为空", trigger: "change" }],
        subjectId: [{ required: true, message: "科目不能为空", trigger: "change" }],
        teacherId: [{ required: true, message: "教师不能为空", trigger: "change" }],
        cancelHours: [{ required: true, message: "注销课时不能为空", trigger: "blur" }]
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
      listCancellation(this.queryParams).then(response => {
        this.cancellationList = response.rows
        this.total = response.total
        this.loading = false
      })
    },
    loadOptions() {
      getAllStudents().then(response => { this.studentOptions = response.data })
      getAllSubjects().then(response => { this.subjectOptions = response.data })
      getAllTeachers().then(response => { this.teacherOptions = response.data })
    },
    cancel() {
      this.open = false
      this.reset()
    },
    reset() {
      this.form = { studentId: null, subjectId: null, teacherId: null, cancelHours: 1, remark: undefined, photoUrl: undefined }
      this.photoList = []
      this.resetForm("form")
    },
    handlePhotoUpload(response) {
      if (response.code === 200) {
        this.form.photoUrl = response.url
        this.$modal.msgSuccess("照片上传成功")
      } else {
        this.$modal.msgError("照片上传失败")
      }
    },
    beforePhotoUpload(file) {
      const isImage = file.type === 'image/jpeg' || file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 2
      if (!isImage) {
        this.$modal.msgError('只能上传jpg/png格式的图片')
        return false
      }
      if (!isLt2M) {
        this.$modal.msgError('图片大小不能超过2MB')
        return false
      }
      return true
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
      this.title = "课时注销"
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          addCancellation(this.form).then(() => {
            this.$modal.msgSuccess("注销成功")
            this.open = false
            this.getList()
          })
        }
      })
    },
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除注销记录编号为"' + ids + '"的数据项？').then(function() {
        return delCancellation(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    handleExport() {
      this.download('education/cancellation/export', { ...this.queryParams }, `cancellation_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>