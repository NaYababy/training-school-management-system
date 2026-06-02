import request from '@/utils/request'

export function listClassroom(query) {
  return request({
    url: '/education/classroom/list',
    method: 'get',
    params: query
  })
}

export function getClassroom(id) {
  return request({
    url: '/education/classroom/' + id,
    method: 'get'
  })
}

export function addClassroom(data) {
  return request({
    url: '/education/classroom',
    method: 'post',
    data: data
  })
}

export function updateClassroom(data) {
  return request({
    url: '/education/classroom',
    method: 'put',
    data: data
  })
}

export function delClassroom(id) {
  return request({
    url: '/education/classroom/' + id,
    method: 'delete'
  })
}

export function getAllClassrooms() {
  return request({
    url: '/education/classroom/all',
    method: 'get'
  })
}