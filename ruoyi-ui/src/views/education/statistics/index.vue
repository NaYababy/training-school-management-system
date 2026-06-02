<template>
  <div class="app-container">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>数据概览</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="refreshData">刷新数据</el-button>
      </div>
      <div class="grid">
        <el-row :gutter="20">
          <el-col :span="6">
            <div class="grid-item">
              <div class="grid-item-icon bg-blue">
                <i class="el-icon-user"></i>
              </div>
              <div class="grid-item-content">
                <div class="grid-item-title">学生总数</div>
                <div class="grid-item-value">{{ overview.totalStudents || 0 }}</div>
              </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-item">
              <div class="grid-item-icon bg-green">
                <i class="el-icon-clock"></i>
              </div>
              <div class="grid-item-content">
                <div class="grid-item-title">总课时</div>
                <div class="grid-item-value">{{ overview.totalHours || 0 }}</div>
              </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-item">
              <div class="grid-item-icon bg-orange">
                <i class="el-icon-check"></i>
              </div>
              <div class="grid-item-content">
                <div class="grid-item-title">已注销课时</div>
                <div class="grid-item-value">{{ overview.canceledHours || 0 }}</div>
              </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-item">
              <div class="grid-item-icon bg-red">
                <i class="el-icon-circle-plus"></i>
              </div>
              <div class="grid-item-content">
                <div class="grid-item-title">剩余课时</div>
                <div class="grid-item-value">{{ overview.remainingHours || 0 }}</div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-card>

    <el-card class="box-card" style="margin-top: 20px;">
      <div slot="header" class="clearfix">
        <span>按时间统计</span>
      </div>
      <el-form :model="timeQuery" :inline="true" label-width="80px">
        <el-form-item label="统计类型">
          <el-select v-model="timeQuery.type" @change="getPeriodStatistics">
            <el-option label="今日" value="today" />
            <el-option label="本周" value="week" />
            <el-option label="本月" value="month" />
            <el-option label="自定义" value="custom" />
          </el-select>
        </el-form-item>
        <el-form-item v-if="timeQuery.type === 'custom'" label="开始日期">
          <el-date-picker v-model="timeQuery.startDate" type="date" />
        </el-form-item>
        <el-form-item v-if="timeQuery.type === 'custom'" label="结束日期">
          <el-date-picker v-model="timeQuery.endDate" type="date" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getPeriodStatistics">查询</el-button>
        </el-form-item>
      </el-form>
      <div class="grid" style="margin-top: 20px;">
        <el-row :gutter="20">
          <el-col :span="6">
            <div class="grid-item">
              <div class="grid-item-icon bg-purple">
                <i class="el-icon-user-plus"></i>
              </div>
              <div class="grid-item-content">
                <div class="grid-item-title">新增学生</div>
                <div class="grid-item-value">{{ periodStats.newStudentCount || 0 }}</div>
              </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-item">
              <div class="grid-item-icon bg-cyan">
                <i class="el-icon-money"></i>
              </div>
              <div class="grid-item-content">
                <div class="grid-item-title">收入金额</div>
                <div class="grid-item-value">{{ periodStats.totalFee || 0 }}</div>
              </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-item">
              <div class="grid-item-icon bg-pink">
                <i class="el-icon-document"></i>
              </div>
              <div class="grid-item-content">
                <div class="grid-item-title">注销学生数</div>
                <div class="grid-item-value">{{ periodStats.cancelStudentCount || 0 }}</div>
              </div>
            </div>
          </el-col>
          <el-col :span="6">
            <div class="grid-item">
              <div class="grid-item-icon bg-yellow">
                <i class="el-icon-s-data"></i>
              </div>
              <div class="grid-item-content">
                <div class="grid-item-title">注销课时</div>
                <div class="grid-item-value">{{ periodStats.totalCancelHours || 0 }}</div>
              </div>
            </div>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>
import { getOverview, getPeriodStatistics } from "@/api/education/statistics"

export default {
  name: "Statistics",
  data() {
    return {
      overview: {},
      periodStats: {},
      timeQuery: { type: 'today', startDate: undefined, endDate: undefined }
    }
  },
  created() {
    this.refreshData()
    this.getPeriodStatistics()
  },
  methods: {
    refreshData() {
      getOverview().then(response => {
        this.overview = response.data
      })
    },
    getPeriodStatistics() {
      let startDate = ''
      let endDate = ''
      const today = new Date()
      
      if (this.timeQuery.type === 'today') {
        startDate = this.formatDate(today)
        endDate = this.formatDate(today)
      } else if (this.timeQuery.type === 'week') {
        const day = today.getDay() || 7
        const monday = new Date(today)
        monday.setDate(today.getDate() - day + 1)
        startDate = this.formatDate(monday)
        endDate = this.formatDate(today)
      } else if (this.timeQuery.type === 'month') {
        startDate = today.getFullYear() + '-' + String(today.getMonth() + 1).padStart(2, '0') + '-01'
        endDate = this.formatDate(today)
      } else if (this.timeQuery.type === 'custom') {
        if (this.timeQuery.startDate && this.timeQuery.endDate) {
          startDate = this.formatDate(this.timeQuery.startDate)
          endDate = this.formatDate(this.timeQuery.endDate)
        } else {
          return
        }
      }
      
      getPeriodStatistics(startDate, endDate).then(response => {
        this.periodStats = response.data
      })
    },
    formatDate(date) {
      if (typeof date === 'string') return date
      return date.getFullYear() + '-' + String(date.getMonth() + 1).padStart(2, '0') + '-' + String(date.getDate()).padStart(2, '0')
    }
  }
}
</script>

<style scoped>
.grid {
  padding: 20px 0;
}
.grid-item {
  display: flex;
  align-items: center;
  padding: 15px;
  background: #f5f7fa;
  border-radius: 8px;
}
.grid-item-icon {
  width: 48px;
  height: 48px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-right: 15px;
}
.grid-item-icon i {
  font-size: 24px;
  color: #fff;
}
.grid-item-content {
  flex: 1;
}
.grid-item-title {
  font-size: 14px;
  color: #999;
  margin-bottom: 5px;
}
.grid-item-value {
  font-size: 24px;
  font-weight: bold;
  color: #333;
}
.bg-blue { background: #1890ff; }
.bg-green { background: #52c41a; }
.bg-orange { background: #fa8c16; }
.bg-red { background: #f5222d; }
.bg-purple { background: #722ed1; }
.bg-cyan { background: #13c2c2; }
.bg-pink { background: #eb2f96; }
.bg-yellow { background: #faad14; }
</style>