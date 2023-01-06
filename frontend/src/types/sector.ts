export default interface ISector {
  id: string | undefined;
  name: string;
  subSectors: ISector[];
  label: string; // Not present in the backend. This attribute is used to display the name of the sector in the UI
}
