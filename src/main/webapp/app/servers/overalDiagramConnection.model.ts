export class OveralDiagramConnection {
    constructor(
        public id?: number,
        public srcServerName?: string,
        public destServerName?: string,
        public srcNetworkCardName?: string,
        public destNetworkCardName?: string
    ) {
        this.id = id ? id : null;
        this.srcServerName = srcServerName ? srcServerName : "";
        this.destServerName = destServerName ? destServerName : "";
        this.srcNetworkCardName = srcNetworkCardName ? srcNetworkCardName : "";
        this.destNetworkCardName = destNetworkCardName ? destNetworkCardName : "";
    }
}
