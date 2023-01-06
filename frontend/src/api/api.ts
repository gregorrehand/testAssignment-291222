import {
  AxiosRequestConfig,
  AxiosResponse
} from './../../node_modules/axios/index.d'
import ISector from '@/types/sector'
import axios from 'axios'
import IReply from '@/types/reply'

const baseUrl = 'http://localhost:8080/'

export const axiosInstance = axios.create({
  baseURL: baseUrl,
  headers: {
    'Content-Type': 'application/json'
  },
  withCredentials: true
})

export const getSectors = async (): Promise<AxiosResponse<ISector[]>> => {
  const response = await axiosInstance.request(<AxiosRequestConfig>{
    method: 'get',
    url: 'sectors'
  })
  return response
}

export const getReplyById = async (id: string): Promise<AxiosResponse<ISector[]>> => {
  const response = await axiosInstance.request(<AxiosRequestConfig>{
    method: 'get',
    url: 'replys/' + id
  })
  return response
}

export const postReply = async (reply: IReply): Promise<AxiosResponse> => {
  const response = await axiosInstance.request(<AxiosRequestConfig>{
    method: 'post',
    url: 'replys',
    data: reply
  })
  return response
}

export const putReply = async (reply: IReply): Promise<AxiosResponse> => {
  return await axiosInstance.request(<AxiosRequestConfig>{
    method: 'put',
    url: 'replys/' + reply.id,
    data: reply
  })
}

export const getSessionReply = async (): Promise<AxiosResponse<any, any> | undefined> => {
  return await axiosInstance
    .request(<AxiosRequestConfig>{
      method: 'get',
      url: 'session'
    })
    .then(async (response) => {
      if (response.data !== '') {
        return await getReplyById(response.data)
      }
      return undefined
    })
}

export const clearSession = async (): Promise<AxiosResponse> => {
  return await axiosInstance.request(<AxiosRequestConfig>{
    method: 'delete',
    url: 'session'
  })
}
