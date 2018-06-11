export class NetworkDiagramComponentProperties{
    constructor(
        public name?: string,
        public ipAddress?: string,
        public cpu?: string,
        public ram?: string,
        public isDedicatedRes?: boolean
        
    ) {
        this.name = "";
        this.ipAddress = "";
        this.cpu = "0";
        this.ram = "0";
        this.isDedicatedRes = false;
    }
}
