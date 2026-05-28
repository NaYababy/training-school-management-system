import request from '@/utils/request'

export function listStudent(query) {
  return request({
    url: '/education/student/list',
    method: 'get',
    params: query
  })
}

export function getStudent(id) {
  return request({
    url: '/education/student/' + id,
    method: 'get'
  })
}

export function addStudent(data) {
  return request({
    url: '/education/student',
    method: 'post',
    data: data
  })
}

export function updateStudent(data) {
  return request({
    url: '/education/student',
    method: 'put',
    data: data
  })
}

export function delStudent(id) {
  return request({
    url: '/education/student/' + id,
    method: 'delete'
  })
}

export function getStudentSubjects(studentId) {
  return request({
    url: '/education/student/subject/' + studentId,
    method: 'get'
  })
}

export function getAllStudents() {
  return request({
    url: '/education/student/all',
    method: 'get'
  })
}