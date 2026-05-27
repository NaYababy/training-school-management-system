<template>
  <div class="app-container home">
    <div class="welcome-box">
      <h1>{{ welcomeText }}</h1>

      <div class="time">
        {{ currentTime }}
      </div>

      <div class="tip">
        欢迎进入培训学校管理系统后台
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Index",
  data() {
    return {
      currentTime: "",
      welcomeText: ""
    }
  },
  mounted() {
    this.updateTime()
    setInterval(this.updateTime, 1000)
  },
  methods: {
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
      const h = String(now.getHours()).padStart(2, "0")
      const m = String(now.getMinutes()).padStart(2, "0")
      const s = String(now.getSeconds()).padStart(2, "0")

      this.currentTime =
        `${year}-${month}-${day} ${h}:${m}:${s}`
    }
  }
}
</script>

<style scoped>
.home {
  height: calc(100vh - 84px);
  display: flex;
  justify-content: center;
  align-items: center;
  background: #f5f7fa;
}

.welcome-box {
  text-align: center;
}

h1 {
  font-size: 42px;
  color: #303133;
  margin-bottom: 20px;
}

.time {
  font-size: 28px;
  color: #409EFF;
  margin-bottom: 15px;
}

.tip {
  font-size: 16px;
  color: #909399;
}
</style>