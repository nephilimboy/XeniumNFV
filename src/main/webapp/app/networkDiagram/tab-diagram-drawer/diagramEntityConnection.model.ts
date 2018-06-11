export class DiagramEntityConnection {
    constructor(public id?: number,
                public key?: string,
                public srcEntityKey?: string,
                public destEntityKey?: string) {
        this.id = id ? id : null;
        this.key = key ? key : '';
        this.srcEntityKey = srcEntityKey ? srcEntityKey : '';
        this.destEntityKey = destEntityKey ? destEntityKey : '';
    }
}
