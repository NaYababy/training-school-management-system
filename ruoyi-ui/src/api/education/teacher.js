import request from '@/utils/request'

export function listTeacher(query) {
  return request({
    url: '/education/teacher/list',
    method: 'get',
    params: query
  })
}

export function getTeacher(id) {
  return request({
    url: '/education/teacher/' + id,
    method: 'get'
  })
}

export function addTeacher(data) {
  return request({
    url: '/education/teacher',
    method: 'post',
    data: data
  })
}

export function updateTeacher(data) {
  return request({
    url: '/education/teacher',
    method: 'put',
    data: data
  })
}

export function delTeacher(id) {
  return request({
    url: '/education/teacher/' + id,
    method: 'delete'
  })
}

export function getTeacherSubjects(teacherId) {
  return request({
    url: '/education/teacher/subject/' + teacherId,
    method: 'get'
  })
}

export function getAllTeachers() {
  return request({
    url: '/education/teacher/all',
    method: 'get'
  })
}