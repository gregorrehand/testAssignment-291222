export default interface ISector {
  id: string | undefined
  name: string
  parentSector: ISector | undefined
}
