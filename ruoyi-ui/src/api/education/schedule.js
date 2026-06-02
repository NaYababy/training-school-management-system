import request from '@/utils/request'

export function listSchedule(query) {
  return request({
    url: '/education/schedule/list',
    method: 'get',
    params: query
  })
}

export function getSchedule(id) {
  return request({
    url: '/education/schedule/' + id,
    method: 'get'
  })
}

export function addSchedule(data) {
  return request({
    url: '/education/schedule',
    method: 'post',
    data: data
  })
}

export function updateSchedule(data) {
  return request({
    url: '/education/schedule',
    method: 'put',
    data: data
  })
}

export function delSchedule(id) {
  return request({
    url: '/education/schedule/' + id,
    method: 'delete'
  })
}

export function getTeacherSchedule(teacherId) {
  return request({
    url: '/education/schedule/teacher/' + teacherId,
    method: 'get'
  })
}