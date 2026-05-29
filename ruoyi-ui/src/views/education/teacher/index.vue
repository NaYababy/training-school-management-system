<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="教师姓名" prop="teacherName">
        <el-input
          v-model="queryParams.teacherName"
          placeholder="请输入教师姓名"
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
          v-hasPermi="['education:teacher:add']"
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
          v-hasPermi="['education:teacher:edit']"
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
          v-hasPermi="['education:teacher:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['education:teacher:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="teacherList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="教师姓名" align="center" prop="teacherName" :show-overflow-tooltip="true" />
      <el-table-column label="手机号码" align="center" prop="phone" :show-overflow-tooltip="true" />
      <el-table-column label="抽成比例" align="center" prop="commissionRate">
        <template slot-scope="scope">
          {{ scope.row.commissionRate }}%
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
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
            v-hasPermi="['education:teacher:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['education:teacher:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-list"
            @click="handleViewSubjects(scope.row)"
            v-hasPermi="['education:teacher:hours']"
          >授课科目</el-button>
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
        <el-form-item label="教师姓名" prop="teacherName">
          <el-input v-model="form.teacherName" placeholder="请输入教师姓名" />
        </el-form-item>
        <el-form-item label="登录密码" prop="password">
          <el-input v-model="form.password" type="password" placeholder="请输入登录密码" />
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号码" />
        </el-form-item>
        <el-form-item label="抽成比例" prop="commissionRate">
          <el-input-number v-model="form.commissionRate" :min="0" :max="100" />
          <span style="margin-left: 5px;">%</span>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="授课科目">
          <el-table :data="form.subjectList" border>
            <el-table-column label="科目" width="150">
              <template slot-scope="scope">
                <el-select v-model="scope.row.subjectId" placeholder="请选择科目">
                  <el-option v-for="subject in subjectOptions" :key="subject.id" :label="subject.subjectName" :value="subject.id" />
                </el-select>
              </template>
            </el-table-column>
            <el-table-column label="主带科目" width="100">
              <template slot-scope="scope">
                <el-switch v-model="scope.row.isMain" :active-value="1" :inactive-value="0" />
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

    <el-dialog title="授课科目" :visible.sync="subjectDialogOpen" width="600px" append-to-body>
      <el-table :data="subjectList" border>
        <el-table-column label="科目名称" prop="subjectName" />
        <el-table-column label="是否主带" prop="isMain">
          <template slot-scope="scope">
            {{ scope.row.isMain === 1 ? '是' : '否' }}
          </template>
        </el-table-column>
      </el-table>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="subjectDialogOpen = false">关 闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTeacher, getTeacher, delTeacher, addTeacher, updateTeacher } from "@/api/education/teacher"
import { getAllSubjects } from "@/api/education/subject"

export default {
  name: "Teacher",
  data() {
    return {
      loading: true,
      ids: [],
      single: true,
      multiple: true,
      showSearch: true,
      total: 0,
      teacherList: [],
      title: "",
      open: false,
      subjectDialogOpen: false,
      subjectList: [],
      subjectOptions: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        teacherName: undefined,
        phone: undefined
      },
      form: {
        subjectList: []
      },
      rules: {
        teacherName: [
          { required: true, message: "教师姓名不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "登录密码不能为空", trigger: "blur" }
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
      listTeacher(this.queryParams).then(response => {
          this.teacherList = response.rows
          this.total = response.total
          this.loading = false
        }
      )
    },
    loadOptions() {
      getAllSubjects().then(response => {
        this.subjectOptions = response.data
      })
    },
    cancel() {
      this.open = false
      this.reset()
    },
    reset() {
      this.form = {
        id: undefined,
        teacherName: undefined,
        password: undefined,
        phone: undefined,
        commissionRate: 0,
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
      this.form.subjectList = [{ subjectId: null, isMain: 0 }]
      this.open = true
      this.title = "添加教师"
    },
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length != 1
      this.multiple = !selection.length
    },
    handleUpdate(row) {
      this.reset()
      const id = row.id || this.ids
      getTeacher(id).then(response => {
        this.form = response.data
        this.form.password = ''
        if (!this.form.subjectList || this.form.subjectList.length === 0) {
          this.form.subjectList = [{ subjectId: null, isMain: 0 }]
        }
        this.open = true
        this.title = "修改教师"
      })
    },
    handleViewSubjects(row) {
      this.subjectList = []
      getTeacher(row.id).then(response => {
        this.subjectList = response.subjectList || []
        this.subjectDialogOpen = true
      })
    },
    addSubjectRow() {
      this.form.subjectList.push({ subjectId: null, isMain: 0 })
    },
    removeSubjectRow(index) {
      if (this.form.subjectList.length > 1) {
        this.form.subjectList.splice(index, 1)
      }
    },
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          const validSubjects = this.form.subjectList.filter(s => s.subjectId)
          this.form.subjectList = validSubjects
          
          if (this.form.id != undefined) {
            updateTeacher(this.form).then(() => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addTeacher(this.form).then(() => {
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
      this.$modal.confirm('是否确认删除教师编号为"' + ids + '"的数据项？').then(function() {
        return delTeacher(ids)
      }).then(() => {
        this.getList()
        this.$modal.msgSuccess("删除成功")
      }).catch(() => {})
    },
    handleExport() {
      this.download('education/teacher/export', {
        ...this.queryParams
      }, `teacher_${new Date().getTime()}.xlsx`)
    }
  }
}
</script>