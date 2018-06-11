import {Server} from '../servers/server.model';
import {OveralDiagramConnection} from '../servers/overalDiagramConnection.model';
import {Topology} from './tab-diagram-drawer/topology.model';
import {Switch} from './accessory-switch/swicth.model';
import {Vnf} from './accessory-vnfs/vnf.model';
import {Controller} from './accessory-controller/controller.model';
import {DiagramEntityConnection} from './tab-diagram-drawer/diagramEntityConnection.model';

export class JsonToNetworkDiagramParser {
    constructor() {
    }

    parsJson(servers: Server[], jSonStr: any) {
        console.log(JSON.parse(jSonStr));
        // Clear overalDiagramConnection inside each server
        console.log(servers);
        servers.forEach(server => {
            server.overalDiagramConnections.splice(0, server.overalDiagramConnections.length)
        });
        JSON.parse(jSonStr).linkDataArray.forEach(element => {
            servers.forEach(server => {
                if (server.name === element.from) {
                    server.overalDiagramConnections.push(new OveralDiagramConnection(null, element.from, element.to, element.fromPort, element.toPort))
                }
            });
        });
        return servers;
    }

    convertDiagramJsonToTopology(jSonStr: any) {
        let topology: Topology = new Topology();
        JSON.parse(jSonStr).nodeDataArray.forEach(element => {
            if (element.name === 'Switch') {
                console.log(element.name);
                topology.switches.push(new Switch(
                    element.source,
                    element.key,
                    element.name,
                    element.describtion,
                    element.loc,
                    [''],
                    [''],
                    [''],
                    [''],
                    new Array()
                    )
                )
            }
            else if (element.name === 'VNF') {
                topology.vnfs.push(new Vnf(
                    element.source,
                    element.key,
                    element.name,
                    element.describtion,
                    element.loc,
                    element.ipAddress,
                    element.cpu,
                    element.ram,
                    element.isDedicatedRes,
                    [''],
                    [''],
                    [''],
                    [''],
                    new Array()
                ));
            }
            else if (element.name === 'Controller') {
                topology.controllers.push(new Controller(
                    element.source,
                    element.key,
                    element.name,
                    element.describtion,
                    element.loc,
                    element.ipAddress,
                    element.cpu,
                    element.ram,
                    element.isDedicatedRes,
                    [''],
                    [''],
                    [''],
                    [''],
                    new Array()
                ));
            }
        });
        JSON.parse(jSonStr).linkDataArray.forEach(element => {
            // CHECK FROM 'S
            if (element.from.split('#')[0] === 'VNF') {
                topology.vnfs.forEach(vnf => {
                    if (vnf.key === element.from) {
                        vnf.diagramEntityConnections.push(new DiagramEntityConnection(
                            -1,
                            element.from + '%' + element.to,
                            element.from,
                            element.to
                        ));
                        topology.diagramEntityConnections.push(new DiagramEntityConnection(
                            -1,
                            element.from + '%' + element.to,
                            element.from,
                            element.to
                        ));
                    }
                })
            }
            else if (element.from.split('#')[0] === 'Switch') {
                topology.switches.forEach(swtch => {
                    if (swtch.key === element.from) {
                        swtch.diagramEntityConnections.push(new DiagramEntityConnection(
                            -1,
                            element.from + '%' + element.to,
                            element.from,
                            element.to
                        ));
                        topology.diagramEntityConnections.push(new DiagramEntityConnection(
                            -1,
                            element.from + '%' + element.to,
                            element.from,
                            element.to
                        ));
                    }
                })
            }
            else if (element.from.split('#')[0] === 'Controller') {
                topology.controllers.forEach(ctr => {
                    if (ctr.key === element.from) {
                        ctr.diagramEntityConnections.push(new DiagramEntityConnection(
                            -1,
                            element.from + '%' + element.to,
                            element.from,
                            element.to
                        ));
                        topology.diagramEntityConnections.push(new DiagramEntityConnection(
                            -1,
                            element.from + '%' + element.to,
                            element.from,
                            element.to
                        ));
                    }
                })
            }
            // CHECK TOOOO 'S
            if (element.to.split('#')[0] === 'VNF') {
                topology.vnfs.forEach(vnf => {
                    if (vnf.key === element.to) {
                        vnf.diagramEntityConnections.push(new DiagramEntityConnection(
                            -1,
                            element.from + '%' + element.to,
                            element.from,
                            element.to
                        ))
                    }
                })
            }
            else if (element.to.split('#')[0] === 'Switch') {
                topology.switches.forEach(swtch => {
                    if (swtch.key === element.to) {
                        swtch.diagramEntityConnections.push(new DiagramEntityConnection(
                            -1,
                            element.from + '%' + element.to,
                            element.from,
                            element.to
                        ))
                    }
                })
            }
            else if (element.to.split('#')[0] === 'Controller') {
                topology.controllers.forEach(ctr => {
                    if (ctr.key === element.to) {
                        ctr.diagramEntityConnections.push(new DiagramEntityConnection(
                            -1,
                            element.from + '%' + element.to,
                            element.from,
                            element.to
                        ))
                    }
                })
            }


        });
        return topology;
    }
}


export class NetworkDiagramToJsonParser {

    constructor() {
    }

    parsNetworkDiagram(servers: Server[]) {

    }

}
