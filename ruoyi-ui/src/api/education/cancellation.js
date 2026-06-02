import request from '@/utils/request'

export function listCancellation(query) {
  return request({
    url: '/education/cancellation/list',
    method: 'get',
    params: query
  })
}

export function getCancellation(id) {
  return request({
    url: '/education/cancellation/' + id,
    method: 'get'
  })
}

export function addCancellation(data) {
  return request({
    url: '/education/cancellation',
    method: 'post',
    data: data
  })
}

export function updateCancellation(data) {
  return request({
    url: '/education/cancellation',
    method: 'put',
    data: data
  })
}

export function delCancellation(id) {
  return request({
    url: '/education/cancellation/' + id,
    method: 'delete'
  })
}