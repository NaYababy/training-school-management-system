import request from '@/utils/request'

export function listSubject(query) {
  return request({
    url: '/education/subject/list',
    method: 'get',
    params: query
  })
}

export function getSubject(id) {
  return request({
    url: '/education/subject/' + id,
    method: 'get'
  })
}

export function addSubject(data) {
  return request({
    url: '/education/subject',
    method: 'post',
    data: data
  })
}

export function updateSubject(data) {
  return request({
    url: '/education/subject',
    method: 'put',
    data: data
  })
}

export function delSubject(id) {
  return request({
    url: '/education/subject/' + id,
    method: 'delete'
  })
}

export function getAllSubjects() {
  return request({
    url: '/education/subject/all',
    method: 'get'
  })
}