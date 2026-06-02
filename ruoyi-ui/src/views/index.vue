<template>
  <div class="dashboard-container">
    <div class="welcome-section">
      <div class="welcome-content">
        <div class="welcome-text">
          <h1>{{ welcomeText }}，管理员 <span class="wave">👋</span></h1>
          <p class="welcome-desc">欢迎来到培训学校管理系统</p>
          <div class="divider"></div>
          <div class="info-row">
            <div class="info-item">
              <span class="icon-calendar">📅</span>
              <span>{{ currentDate }}</span>
            </div>
            <div class="info-item">
              <span class="icon-weather">☀️</span>
              <span>天气: 晴 25°C</span>
            </div>
            <div class="info-item">
              <span class="icon-location">📍</span>
              <span>北京市</span>
            </div>
          </div>
        </div>
        <div class="welcome-decoration">
          <div class="computer">
            <div class="screen">
              <div class="screen-content">
                <div class="screen-header">
                  <div class="dot red"></div>
                  <div class="dot yellow"></div>
                  <div class="dot green"></div>
                </div>
                <div class="charts">
                  <div class="chart-bar">
                    <div class="bar" style="height: 60%"></div>
                    <div class="bar" style="height: 80%"></div>
                    <div class="bar" style="height: 45%"></div>
                    <div class="bar" style="height: 90%"></div>
                    <div class="bar" style="height: 70%"></div>
                  </div>
                  <div class="chart-line"></div>
                </div>
              </div>
            </div>
            <div class="stand"></div>
          </div>
          <div class="books">📚</div>
          <div class="plant">🪴</div>
        </div>
      </div>
    </div>

    <div class="stats-section">
      <el-row :gutter="20">
        <el-col :xs="24" :sm="12" :lg="6">
          <div class="stat-card students">
            <div class="stat-icon">
              <span class="icon">👥</span>
            </div>
            <div class="stat-info">
              <div class="stat-label">学生总数</div>
              <div class="stat-value">{{ dashboardStats.studentCount }}</div>
            </div>
          </div>
        </el-col>
        <el-col :xs="24" :sm="12" :lg="6">
          <div class="stat-card courses">
            <div class="stat-icon">
              <span class="icon">📚</span>
            </div>
            <div class="stat-info">
              <div class="stat-label">科目总数</div>
              <div class="stat-value">{{ dashboardStats.subjectCount }}</div>
            </div>
          </div>
        </el-col>
        <el-col :xs="24" :sm="12" :lg="6">
          <div class="stat-card teachers">
            <div class="stat-icon">
              <span class="icon">👨‍🏫</span>
            </div>
            <div class="stat-info">
              <div class="stat-label">教师总数</div>
              <div class="stat-value">{{ dashboardStats.teacherCount }}</div>
            </div>
          </div>
        </el-col>
        <el-col :xs="24" :sm="12" :lg="6">
          <div class="stat-card revenue">
            <div class="stat-icon">
              <span class="icon">💰</span>
            </div>
            <div class="stat-info">
              <div class="stat-label">累计缴费(元)</div>
              <div class="stat-value">{{ dashboardStats.totalFee }}</div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>

    <div class="main-content">
      <el-row :gutter="20">
        <el-col :xs="24" :sm="12" :lg="6">
          <div class="content-card">
            <div class="card-header">
              <span class="header-icon">📢</span>
              <span class="header-title">系统公告</span>
              <span class="more-link">更多 > </span>
            </div>
            <div class="card-body">
              <ul class="notice-list">
                <li v-for="(notice, index) in noticeList" :key="index" class="notice-item">
                  <span class="dot"></span>
                  <span class="text">{{ notice.title }}</span>
                  <span class="date">{{ notice.date }}</span>
                </li>
              </ul>
            </div>
          </div>
        </el-col>

        <el-col :xs="24" :sm="12" :lg="6">
          <div class="content-card">
            <div class="card-header">
              <span class="header-icon">📋</span>
              <span class="header-title">待办事项</span>
              <span class="more-link">更多 > </span>
            </div>
            <div class="card-body">
              <ul class="todo-list">
                <li v-for="(item, index) in todoList" :key="index" class="todo-item">
                  <span :class="['tag', item.tagClass]">{{ item.count }}</span>
                  <span class="text">{{ item.title }}</span>
                </li>
              </ul>
            </div>
          </div>
        </el-col>

        <el-col :xs="24" :sm="12" :lg="6">
          <div class="content-card">
            <div class="card-header">
              <span class="header-icon">⚡</span>
              <span class="header-title">快捷入口</span>
            </div>
            <div class="card-body">
              <div class="quick-entries">
                <div v-for="(entry, index) in quickEntries" :key="index" class="entry-item">
                  <div :class="['entry-icon', entry.bgClass]">{{ entry.icon }}</div>
                  <span class="entry-name">{{ entry.name }}</span>
                </div>
              </div>
            </div>
          </div>
        </el-col>

        <el-col :xs="24" :sm="12" :lg="6">
          <div class="content-card schedule-card">
            <div class="card-header">
              <span class="header-icon">📅</span>
              <span class="header-title">今日课表</span>
              <span class="more-link">更多 > </span>
            </div>
            <div class="card-body">
              <div class="schedule-header">
                <span>{{ currentDate }}</span>
              </div>
              <div class="schedule-list">
                <div v-for="(classItem, index) in scheduleList" :key="index" class="schedule-item">
                  <div class="time">{{ classItem.time }}</div>
                  <div class="class-info">
                    <div class="class-name">{{ classItem.name }}</div>
                    <div class="class-detail">{{ classItem.teacher }} · {{ classItem.room }}</div>
                  </div>
                  <span :class="['status', classItem.status]">{{ classItem.statusText }}</span>
                </div>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>

    <div class="quote-section">
      <div class="quote-content">
        <div class="quote-text">
          <span class="quote-start">"</span>
          <span>教育不是灌输，而是点燃火焰。</span>
          <span class="quote-end">"</span>
        </div>
        <div class="quote-author">—— 苏格拉底</div>
        <div class="quote-decoration">
          <span class="book">📖</span>
          <span class="plant">🪴</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { getDashboardStatistics } from "@/api/education/statistics"

export default {
  name: "Index",
  data() {
    return {
      currentTime: "",
      currentDate: "",
      welcomeText: "",
      dashboardStats: {
        studentCount: 0,
        subjectCount: 0,
        teacherCount: 0,
        classroomCount: 0,
        totalFee: 0
      },
      noticeList: [
        { title: "关于2025年暑期课程安排的通知", date: "05-20" },
        { title: "关于2025年暑期课程安排的通知", date: "05-20" },
        { title: "系统维护升级公告", date: "05-18" },
        { title: "教师考核制度更新说明", date: "05-15" },
        { title: "教师考核制度放假通知", date: "05-15" },
        { title: "五一劳动节放假通知", date: "04-28" },
        { title: "学员续费优惠活动即将开始", date: "04-25" }
      ],
      todoList: [
        { title: "待审核的报名申请", count: 12, tagClass: "tag-red" },
        { title: "待审核的报名申请", count: 12, tagClass: "tag-red" },
        { title: "待处理的请假申请", count: 6, tagClass: "tag-orange" },
        { title: "待审批的费用报销", count: 3, tagClass: "tag-yellow" },
        { title: "待发送的课程通知", count: 5, tagClass: "tag-blue" },
        { title: "待确认的教师排课", count: 8, tagClass: "tag-purple" }
      ],
      quickEntries: [
        { name: "学生管理", icon: "👥", bgClass: "bg-blue" },
        { name: "课程管理", icon: "📚", bgClass: "bg-green" },
        { name: "教师管理", icon: "👨‍🏫", bgClass: "bg-orange" },
        { name: "排课管理", icon: "📅", bgClass: "bg-purple" },
        { name: "缴费管理", icon: "💰", bgClass: "bg-cyan" },
        { name: "成绩管理", icon: "📊", bgClass: "bg-blue-light" },
        { name: "班级管理", icon: "🏫", bgClass: "bg-teal" },
        { name: "考勤管理", icon: "✅", bgClass: "bg-orange-light" },
        { name: "系统设置", icon: "⚙️", bgClass: "bg-gray" }
      ],
      scheduleList: [
        { time: "08:30", name: "Java程序设计基础", teacher: "张老师", room: "A101教室", status: "active", statusText: "进行中" },
        { time: "10:30", name: "数据结构与算法", teacher: "李老师", room: "B202教室", status: "pending", statusText: "未开始" },
        { time: "14:00", name: "前端开发实战", teacher: "王老师", room: "C303教室", status: "pending", statusText: "未开始" },
        { time: "16:00", name: "数据库原理", teacher: "赵老师", room: "D404教室", status: "pending", statusText: "未开始" }
      ]
    }
  },
  mounted() {
    this.updateTime()
    this.loadDashboardStats()
    setInterval(this.updateTime, 1000)
  },
  methods: {
    loadDashboardStats() {
      getDashboardStatistics().then(response => {
        if (response.data) {
          this.dashboardStats = response.data
        }
      })
    },
    updateTime() {
      const now = new Date()
      const hour = now.getHours()

      if (hour < 6) {
        this.welcomeText = "凌晨好"
      } else if (hour < 12) {
        this.welcomeText = "上午好"
      } else if (hour < 18) {
        this.welcomeText = "下午好"
      } else {
        this.welcomeText = "晚上好"
      }

      const year = now.getFullYear()
      const month = String(now.getMonth() + 1).padStart(2, "0")
      const day = String(now.getDate()).padStart(2, "0")
      const weekDays = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"]
      const weekDay = weekDays[now.getDay()]

      this.currentDate = `${year}年${month}月${day}日 ${weekDay}`

      const h = String(now.getHours()).padStart(2, "0")
      const m = String(now.getMinutes()).padStart(2, "0")
      const s = String(now.getSeconds()).padStart(2, "0")

      this.currentTime = `${year}-${month}-${day} ${h}:${m}:${s}`
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard-container {
  padding: 20px;
  background: #f5f7fa;
  min-height: calc(100vh - 84px);
}

.welcome-section {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 16px;
  padding: 30px;
  margin-bottom: 20px;
  color: #fff;
  overflow: hidden;
  position: relative;
}

.welcome-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.welcome-text {
  flex: 1;
}

.welcome-text h1 {
  font-size: 32px;
  font-weight: 600;
  margin-bottom: 8px;
}

.wave {
  font-size: 28px;
}

.welcome-desc {
  font-size: 16px;
  opacity: 0.9;
  margin-bottom: 20px;
}

.divider {
  width: 40px;
  height: 4px;
  background: #fff;
  border-radius: 2px;
  margin-bottom: 20px;
}

.info-row {
  display: flex;
  gap: 30px;
  flex-wrap: wrap;
}

.info-item {
  display: flex;
  align-items: center;
  gap: 8px;
  font-size: 14px;
}

.icon-calendar,
.icon-weather,
.icon-location {
  font-size: 16px;
}

.welcome-decoration {
  display: flex;
  align-items: flex-end;
  gap: 20px;
  position: relative;
}

.computer {
  position: relative;
}

.screen {
  width: 220px;
  height: 140px;
  background: #1a1f36;
  border-radius: 12px;
  padding: 8px;
  box-shadow: 0 20px 40px rgba(0, 0, 0, 0.3);
}

.screen-content {
  width: 100%;
  height: 100%;
  background: linear-gradient(180deg, #2a324a 0%, #1e2538 100%);
  border-radius: 8px;
  padding: 8px;
  box-sizing: border-box;
}

.screen-header {
  display: flex;
  gap: 6px;
  margin-bottom: 10px;
}

.dot {
  width: 8px;
  height: 8px;
  border-radius: 50%;
}

.dot.red {
  background: #ff5f56;
}

.dot.yellow {
  background: #ffbd2e;
}

.dot.green {
  background: #27ca40;
}

.charts {
  display: flex;
  justify-content: space-around;
  align-items: flex-end;
  height: calc(100% - 26px);
}

.chart-bar {
  display: flex;
  gap: 6px;
  align-items: flex-end;
  height: 100%;
}

.bar {
  width: 12px;
  background: linear-gradient(180deg, #667eea 0%, #764ba2 100%);
  border-radius: 4px 4px 0 0;
}

.stand {
  width: 60px;
  height: 20px;
  background: linear-gradient(180deg, #4a5568 0%, #2d3748 100%);
  margin: 0 auto;
  border-radius: 0 0 4px 4px;
}

.books {
  font-size: 40px;
  position: absolute;
  bottom: -5px;
  right: 80px;
}

.plant {
  font-size: 35px;
  position: absolute;
  bottom: -5px;
  right: 0;
}

.stats-section {
  margin-bottom: 20px;
}

.stat-card {
  background: #fff;
  border-radius: 12px;
  padding: 20px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  transition: transform 0.2s, box-shadow 0.2s;
  
  &:hover {
    transform: translateY(-2px);
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
  }
}

.stat-icon {
  width: 56px;
  height: 56px;
  border-radius: 14px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.stat-card.students .stat-icon {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.stat-card.courses .stat-icon {
  background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%);
}

.stat-card.teachers .stat-icon {
  background: linear-gradient(135deg, #fc4a1a 0%, #f7b733 100%);
}

.stat-card.revenue .stat-icon {
  background: linear-gradient(135deg, #a8edea 0%, #fed6e3 100%);
}

.stat-icon .icon {
  font-size: 28px;
}

.stat-info {
  flex: 1;
}

.stat-label {
  font-size: 14px;
  color: #909399;
  margin-bottom: 4px;
}

.stat-value {
  font-size: 28px;
  font-weight: 600;
  color: #303133;
  margin-bottom: 4px;
}

.stat-change {
  font-size: 12px;
  
  &.positive {
    color: #67c23a;
  }
  
  .arrow {
    margin-right: 2px;
  }
}

.main-content {
  margin-bottom: 20px;
}

.content-card {
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.08);
  overflow: hidden;
}

.card-header {
  display: flex;
  align-items: center;
  padding: 16px 20px;
  border-bottom: 1px solid #f0f0f0;
}

.header-icon {
  font-size: 18px;
  margin-right: 8px;
}

.header-title {
  font-size: 16px;
  font-weight: 600;
  color: #303133;
}

.more-link {
  margin-left: auto;
  font-size: 14px;
  color: #409eff;
  cursor: pointer;
}

.card-body {
  padding: 16px 20px;
}

.notice-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.notice-item {
  display: flex;
  align-items: center;
  padding: 10px 0;
  border-bottom: 1px dashed #f0f0f0;
  
  &:last-child {
    border-bottom: none;
  }
  
  .dot {
    width: 6px;
    height: 6px;
    background: #409eff;
    border-radius: 50%;
    margin-right: 10px;
    flex-shrink: 0;
  }
  
  .text {
    flex: 1;
    font-size: 14px;
    color: #606266;
    overflow: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
  }
  
  .date {
    font-size: 12px;
    color: #909399;
    margin-left: 10px;
    flex-shrink: 0;
  }
}

.todo-list {
  list-style: none;
  padding: 0;
  margin: 0;
}

.todo-item {
  display: flex;
  align-items: center;
  padding: 10px 0;
  border-bottom: 1px dashed #f0f0f0;
  
  &:last-child {
    border-bottom: none;
  }
  
  .tag {
    width: 24px;
    height: 24px;
    border-radius: 6px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 12px;
    font-weight: 600;
    color: #fff;
    margin-right: 10px;
    flex-shrink: 0;
  }
  
  .tag-red {
    background: #f56c6c;
  }
  
  .tag-orange {
    background: #e6a23c;
  }
  
  .tag-yellow {
    background: #f9c851;
    color: #8a6d3b;
  }
  
  .tag-blue {
    background: #409eff;
  }
  
  .tag-purple {
    background: #909399;
  }
  
  .text {
    font-size: 14px;
    color: #606266;
  }
}

.quick-entries {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;
}

.entry-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 8px;
  cursor: pointer;
  transition: transform 0.2s;
  
  &:hover {
    transform: translateY(-2px);
  }
}

.entry-icon {
  width: 48px;
  height: 48px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
}

.bg-blue {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.bg-green {
  background: linear-gradient(135deg, #11998e 0%, #38ef7d 100%);
}

.bg-orange {
  background: linear-gradient(135deg, #fc4a1a 0%, #f7b733 100%);
}

.bg-purple {
  background: linear-gradient(135deg, #a855f7 0%, #ec4899 100%);
}

.bg-cyan {
  background: linear-gradient(135deg, #06b6d4 0%, #3b82f6 100%);
}

.bg-blue-light {
  background: linear-gradient(135deg, #3b82f6 0%, #60a5fa 100%);
}

.bg-teal {
  background: linear-gradient(135deg, #14b8a6 0%, #22d3ee 100%);
}

.bg-orange-light {
  background: linear-gradient(135deg, #f97316 0%, #fb923c 100%);
}

.bg-gray {
  background: linear-gradient(135deg, #6b7280 0%, #9ca3af 100%);
}

.entry-name {
  font-size: 12px;
  color: #606266;
}

.schedule-card {
  min-height: 320px;
}

.schedule-header {
  padding-bottom: 12px;
  margin-bottom: 12px;
  border-bottom: 1px solid #f0f0f0;
  font-size: 14px;
  color: #909399;
}

.schedule-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.schedule-item {
  display: flex;
  align-items: center;
  gap: 12px;
}

.time {
  font-size: 14px;
  font-weight: 600;
  color: #303133;
  width: 60px;
  flex-shrink: 0;
}

.class-info {
  flex: 1;
}

.class-name {
  font-size: 14px;
  color: #303133;
  margin-bottom: 2px;
}

.class-detail {
  font-size: 12px;
  color: #909399;
}

.status {
  font-size: 12px;
  padding: 2px 8px;
  border-radius: 4px;
  
  &.active {
    background: #e8f5e9;
    color: #2e7d32;
  }
  
  &.pending {
    background: #f5f5f5;
    color: #909399;
  }
}

.quote-section {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  border-radius: 16px;
  padding: 30px;
  margin-bottom: 20px;
  color: #fff;
}

.quote-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.quote-text {
  font-size: 20px;
  font-weight: 500;
  margin-bottom: 12px;
}

.quote-start,
.quote-end {
  font-size: 28px;
  opacity: 0.7;
  margin: 0 8px;
}

.quote-author {
  font-size: 14px;
  opacity: 0.8;
  margin-bottom: 16px;
}

.quote-decoration {
  display: flex;
  gap: 30px;
  
  .book,
  .plant {
    font-size: 28px;
  }
}

@media (max-width: 768px) {
  .dashboard-container {
    padding: 12px;
  }
  
  .welcome-section {
    padding: 20px;
  }
  
  .welcome-content {
    flex-direction: column;
    text-align: center;
  }
  
  .welcome-text h1 {
    font-size: 24px;
  }
  
  .info-row {
    justify-content: center;
  }
  
  .welcome-decoration {
    margin-top: 20px;
  }
  
  .computer {
    display: none;
  }
  
  .books,
  .plant {
    position: static;
  }
  
  .stat-card {
    flex-direction: column;
    text-align: center;
  }
  
  .quick-entries {
    grid-template-columns: repeat(3, 1fr);
  }
}
</style>