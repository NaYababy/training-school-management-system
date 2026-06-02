import request from '@/utils/request'

export function getDailyStatistics(date) {
  return request({
    url: '/education/statistics/daily',
    method: 'get',
    params: { date }
  })
}

export function getWeeklyStatistics(startDate, endDate) {
  return request({
    url: '/education/statistics/weekly',
    method: 'get',
    params: { startDate, endDate }
  })
}

export function getPeriodStatistics(startDate, endDate) {
  return request({
    url: '/education/statistics/period',
    method: 'get',
    params: { startDate, endDate }
  })
}

export function getSubjectStatistics() {
  return request({
    url: '/education/statistics/subject',
    method: 'get'
  })
}

export function getTeacherStatistics() {
  return request({
    url: '/education/statistics/teacher',
    method: 'get'
  })
}

export function getStudentStatistics() {
  return request({
    url: '/education/statistics/student',
    method: 'get'
  })
}

export function getOverview() {
  return request({
    url: '/education/statistics/overview',
    method: 'get'
  })
}

export function getDashboardStatistics() {
  return request({
    url: '/education/statistics/dashboard',
    method: 'get'
  })
}
