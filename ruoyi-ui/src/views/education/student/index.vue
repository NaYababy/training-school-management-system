<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="学生姓名" prop="studentName">
        <el-input
          v-model="queryParams.studentName"
          placeholder="请输入学生姓名"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号码" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号码"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="年级" prop="grade">
        <el-input
          v-model="queryParams.grade"
          placeholder="请输入年级"
          clearable
          style="width: 240px"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['education:student:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['education:student:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['education:student:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['education:student:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="studentList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="学生姓名" align="center" prop="studentName" :show-overflow-tooltip="true" />
      <el-table-column label="年级" align="center" prop="grade" :show-overflow-tooltip="true" />
      <el-table-column label="性别" align="center" prop="gender" :show-overflow-tooltip="true" />
      <el-table-column label="手机号码" align="center" prop="phone" :show-overflow-tooltip="true" />
      <el-table-column label="总课时" align="center" prop="totalHours" />
      <el-table-column label="剩余课时" align="center" prop="remainingHours" />
      <el-table-column label="缴费金额" align="center" prop="totalFee" />
      <el-table-column label="注册时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['education:student:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['education:student:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-list"
            @click="handleViewSubjects(scope.row)"
            v-hasPermi="['education:student:hours']"
          >课时明细</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="学生姓名" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入学生姓名" />
        </el-form-item>
        <el-form-item label="年级" prop="grade">
          <el-input v-model="form.grade" placeholder="请输入年级" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="form.gender" placeholder="请选择性别">
            <el-option label="男" value="男" />
            <el-option label="女" value="女" />
          </el-select>
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="缴费金额" prop="totalFee">
          <el-input v-model="form.totalFee" placeholder="请输入缴费金额" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="关联科目">
          <el-table :data="form.subjectList" border>
            <el-table-column label="科目" width="120">
              <template slot-scope="scope">
                <el-select v-model="scope.row.subjectId" placeholder="请选择科目" @change="handleSubjectChange(scope.row, scope.$index)">
                  <el-option v-for="subject in subjectOptions" :key="subject.id" :label="subject.subjectName" :value="subject.id" />
                </el-select>
              </template>
            </el-table-column>
            <el-table-column label="教师" width="120">
              <template slot-scope="scope">
                <el-select v-model="scope.row.teacherId" placeholder="请选择教师">
                  <el-option v-for="teacher in teacherOptions[scope.row.subjectId] || []" :key="teacher.id" :label="teacher.teacherName" :value="teacher.id" />
                </el-select>
              </template>
            </el-table-column>
            <el-table-column label="课时数" width="100">
              <template slot-scope="scope">
                <el-input-number v-model="scope.row.subjectHours" :min="1" />
              </template>
            </el-table-column>
            <el-table-column label="操作" width="80">
              <template slot-scope="scope">
                <el-button size="mini" type="text" icon="el-icon-plus" @click="addSubjectRow">+</el-button>
                <el-button size="mini" type="text" icon="el-icon-delete" @click="removeSubjectRow(scope.$index)">-</el-button>
              </template>
            </el-table-column>
          </el-table>
          <el-button type="text" icon="el-icon-plus" @click="addSubjectRow">添加科目</el-button>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="课时明细" :visible.sync="subjectDialogOpen" width="600px" append-to-body>
      <el-table :data="subjectList" border>
        <el-table-column label="科目名称" prop="subjectName" />
        <el-table-column label="授课教师" prop="teacherName" />
        <el-table-column label="总课时" prop="subjectHours" />
        <el-table-column label="剩余课时" prop="subjectRemainingHours" />
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="subjectDialogOpen = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listStudent, getStudent, delStudent, addStudent, updateStudent } from "@/api/education/student"
import { getAllSubjects } from "@/api/education/subject"
import { getAllTeachers, getTeachersBySubject } from "@/api/education/teacher"

export default {
  name: "Student",
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      studentList: [],
      title: "",
      open: false,
      subjectDialogOpen: false,
      subjectList: [],
      subjectOptions: [],
      teacherOptions: {},
      allTeachers: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        studentName: undefined,
        phone: undefined,
        grade: undefined
      },
      form: {
        subjectList: []
      },
      rules: {
        studentName: [
          { required: true, message: "学生姓名不能为空", trigger: "blur" }
        ],
        grade: [
          { required: true, message: "年级不能为空", trigger: "blur" }
        ],
        gender: [
          { required: true, message: "性别不能为空", trigger: "blur" }
        ],
        phone: [
          { required: true, message: "手机号码不能为空", trigger: "blur" }
        ]
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
      listStudent(this.queryParams).then(response => {
          this.studentList = response.rows
          this.total = response.total
          this.loading = false
        }
      )
    },
    loadOptions() {
      getAllSubjects().then(response => {
        this.subjectOptions = response.data
      })
      getAllTeachers().then(response => {
        this.allTeachers = response.data
      })
    },
    getTeachersBySubjectId(subjectId) {
      if (!subjectId) {
        return []
      }
      if (this.teacherOptions[subjectId]) {
        return this.teacherOptions[subjectId]
      }
      getTeachersBySubject(subjectId).then(response => {
        this.$set(this.teacherOptions, subjectId, response.data)
      })
      return []
    },
    handleSubjectChange(row, index) {
      row.teacherId = null
      if (row.subjectId) {
        if (!this.teacherOptions[row.subjectId]) {
          getTeachersBySubject(row.subjectId).then(response => {
            this.$set(this.teacherOptions, row.subjectId, response.data)
          })
        }
      }
    },
    cancel() {
      this.open = false
      this.reset()
    },
    reset() {
      this.form = {
        id: undefined,
        studentName: undefined,
        grade: undefined,
        gender: undefined,
        phone: undefined,
        totalFee: undefined,
        remark: undefined,
        subjectList: []
      }
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
      this.form.subjectList = [{ subjectId: null, teacherId: null, subjectHours: 0 }]
      this.open = true
      this.title = "添加学生"
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getStudent(id).then(response => {
        this.form = response.data
        if (!this.form.subjectList || this.form.subjectList.length === 0) {
          this.form.subjectList = [{ subjectId: null, teacherId: null, subjectHours: 0 }]
        } else {
          this.form.subjectList.forEach(subject => {
            if (subject.subjectId && !this.teacherOptions[subject.subjectId]) {
              getTeachersBySubject(subject.subjectId).then(res => {
                this.$set(this.teacherOptions, subject.subjectId, res.data)
              })
            }
          })
        }
        this.open = true
        this.title = "修改学生"
      })
    },
    handleViewSubjects(row) {
      this.subjectList = []
      getStudent(row.id).then(response => {
        this.subjectList = response.subjectList || []
        this.subjectDialogOpen = true
      })
    },
    addSubjectRow() {
      this.form.subjectList.push({ subjectId: null, teacherId: null, subjectHours: 0 })
    },
    removeSubjectRow(index) {
      if (this.form.subjectList.length > 1) {
        this.form.subjectList.splice(index, 1)
      }
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const validSubjects = this.form.subjectList.filter(s => s.subjectId && s.subjectHours > 0)
          this.form.subjectList = validSubjects
          
          if (this.form.id != undefined) {
            updateStudent(this.form).then(() => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addStudent(this.form).then(() => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    handleDelete(row) {
      const ids = row.id || this.ids
      this.$modal.confirm('是否确认删除学生编号为"' + ids + '"的数据项？').then(function() {
        return delStudent(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    handleExport() {
      this.download('education/student/export', {
        ...this.queryParams
      }, `student_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>