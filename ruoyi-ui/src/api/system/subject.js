import request from '@/utils/request'

export function listSubject(query) {
  return request({
    url: '/system/subject/list',
    method: 'get',
    params: query
  })
}

export function getSubject(id) {
  return request({
    url: '/system/subject/' + id,
    method: 'get'
  })
}

export function addSubject(data) {
  return request({
    url: '/system/subject',
    method: 'post',
    data: data
  })
}

export function updateSubject(data) {
  return request({
    url: '/system/subject',
    method: 'put',
    data: data
  })
}

export function delSubject(id) {
  return request({
    url: '/system/subject/' + id,
    method: 'delete'
  })
}

export function getAllSubjects() {
  return request({
    url: '/system/subject/all',
    method: 'get'
  })
}