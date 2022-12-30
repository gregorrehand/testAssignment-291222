import ISector from './sector'

export default interface IReply {
  id: string | undefined
  name: string
  agreeToTerms: boolean
  sectors: ISector[]
}
