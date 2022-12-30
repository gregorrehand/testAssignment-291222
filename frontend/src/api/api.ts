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
  }
})

export const getSectors = async (): Promise<AxiosResponse<ISector[]>> => {
  return await axiosInstance.request(<AxiosRequestConfig>{
    method: 'get',
    url: 'sectors'
  })
}

export const postReply = async (reply: IReply): Promise<AxiosResponse> => {
  return await axiosInstance.request(<AxiosRequestConfig>{
    method: 'post',
    url: 'replys',
    data: reply
  })
}

export const putReply = async (reply: IReply): Promise<AxiosResponse> => {
  console.log('saveReply()')
  return await axiosInstance.request(<AxiosRequestConfig>{
    method: 'put',
    url: 'replys/' + reply.id,
    data: reply
  })
}
