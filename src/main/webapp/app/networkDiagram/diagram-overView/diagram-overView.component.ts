import {
    Component,
    OnInit,
    ViewChild,
    ElementRef,
    AfterViewInit,
    Input
} from "@angular/core";
import { Server } from "../../servers/server.model";
import { JsonToNetworkDiagramParser } from "../netWorkDiagramParser.util";
import * as go from "gojs";

const $$ = go.GraphObject.make;

@Component({
    selector: "diagram-overView",
    template: `
    <!-- <input type="button" value="clickme" (click)="save()"/> -->
    <div id="myDiagramDiv"  #myDiagramDiv style="border: solid 1px black; width:100%; height:600px"></div>
    `
})
export class DiagramOverView extends go.Link implements OnInit {
    @ViewChild("myDiagramDiv") myDiagramDiv: ElementRef;

    @Input() servers: Server[];

    public myDiagram: any;
    public __this = this;

    public samplePhysicalServer = require("../../../content/img/XenoNet/samplePhysicalServer.png");

    constructor() {
        super();
    }

    // ngOnInit() {}

    ngOnInit() {
        ///////////////////////////////////////////////////////

        //for conciseness in defining node templates

        this.myDiagram = $$(
            go.Diagram,
            this.myDiagramDiv.nativeElement.id, //Diagram refers to its DIV HTML element by id
            {
                initialContentAlignment: go.Spot.Center,
                "undoManager.isEnabled": true
            }
        );

        // when the document is modified, add a "*" to the title and enable the "Save" button
        // this.myDiagram.addDiagramListener("Modified", function (e) {
        // var button = document.getElementById("SaveButton");
        // // if (button) button.disabled = !this.myDiagram.isModified;
        // var idx = document.title.indexOf("*");
        // if (this.myDiagram.isModified) {
        // if (idx < 0) document.title += "*";
        // } else {
        // if (idx >= 0) document.title = document.title.substr(0, idx);
        // }
        // });

        // To simplify this code we define a function for creating a context menu button:

        var nodeMenu = $$(
            // context menu for each Node
            go.Adornment,
            "Vertical",
            this.makeButton("Copy", (e, obj) => {
                e.diagram.commandHandler.copySelection();
            }),
            this.makeButton("Delete", (e, obj) => {
                e.diagram.commandHandler.deleteSelection();
            }),
            $$(go.Shape, "LineH", {
                strokeWidth: 2,
                height: 1,
                stretch: go.GraphObject.Horizontal
            }),
            this.makeButton("Add top port", (e, obj) => {
                this.addPort("top");
            }),
            this.makeButton("Add left port", (e, obj) => {
                this.addPort("left");
            }),
            this.makeButton("Add right port", (e, obj) => {
                this.addPort("right");
            }),
            this.makeButton("Add bottom port", (e, obj) => {
                this.addPort("bottom");
            })
        );

        var portSize = new go.Size(8, 8);

        var portMenu = $$(
            // context menu for each port
            go.Adornment,
            "Vertical",
            this.makeButton(
                "Remove port",
                // in the click event handler, the obj.part is the Adornment;
                // its adornedObject is the port
                function(e, obj) {
                    this.removePort(obj.part.adornedObject);
                }
            ),
            this.makeButton("Change color", (e, obj) => {
                this.changeColor(obj.part.adornedObject);
            }),
            this.makeButton("Remove side ports", (e, obj) => {
                this.removeAll(obj.part.adornedObject);
            })
        );

        // the node template
        // includes a panel on each side with an itemArray of panels containing ports
        this.myDiagram.nodeTemplate = $$(
            go.Node,
            "Table",
            {
                locationObjectName: "BODY",
                locationSpot: go.Spot.Center,
                selectionObjectName: "BODY",
                contextMenu: nodeMenu
            },
            new go.Binding("location", "loc", go.Point.parse).makeTwoWay(
                go.Point.stringify
            ),

            // the body
            $$(
                go.Panel,
                "Auto",
                {
                    row: 1,
                    column: 1,
                    name: "BODY",
                    stretch: go.GraphObject.Fill
                },
                $$(go.Shape, "Rectangle", {
                    fill: "#ffffff",
                    strokeWidth: 1,
                    minSize: new go.Size(220, 220)
                }),

                $$(
                    go.Picture,

                    {
                        // margin: 20,
                        width: 200,
                        height: 200
                    },
                    new go.Binding("source")
                ),
                $$(
                    go.TextBlock,
                    {
                        margin: 2,
                        verticalAlignment: go.Spot.Top,
                        font: "14px  Segoe UI,sans-serif",
                        stroke: "black",
                        textAlign: "center",
                        width: 220,
                        height: 220,
                        editable: true
                    },
                    new go.Binding("text", "name").makeTwoWay()
                ),
                $$(
                    go.TextBlock,
                    {
                        margin: 2,
                        verticalAlignment: go.Spot.Bottom,
                        font: "14px  Segoe UI,sans-serif",
                        stroke: "black",
                        textAlign: "center",
                        width: 220,
                        height: 220,
                        editable: true
                    },
                    new go.Binding("text", "describtion").makeTwoWay()
                )
            ), // end Auto Panel body

            // the Panel holding the bottom port elements, which are themselves Panels,
            // created for each item in the itemArray, bound to data.bottomArray
            $$(
                go.Panel,
                "Horizontal",
                new go.Binding("itemArray", "bottomArray"),
                {
                    row: 2,
                    column: 1,
                    itemTemplate: $$(
                        go.Panel,
                        {
                            _side: "bottom",
                            fromSpot: go.Spot.Bottom,
                            toSpot: go.Spot.Bottom,
                            fromLinkable: true,
                            toLinkable: true,
                            cursor: "pointer"
                            // contextMenu: portMenu
                        },
                        new go.Binding("portId", "portId"),
                        $$(go.Shape, {
                            figure: "PrimitiveToCall",
                            stroke: null,
                            strokeWidth: 0,
                            angle: "90",
                            fill: "#2a1b51",
                            margin: new go.Margin(0, 30),
                            desiredSize: new go.Size(80, 15)
                        }),
                        $$(
                            go.TextBlock,
                            {
                                margin: 1,
                                verticalAlignment: go.Spot.Top,
                                font: "10px  Segoe UI,sans-serif",
                                stroke: "black",
                                angle: "270",
                                textAlign: "center",
                                width: 80,
                                height: 50
                                // editable: true
                            },
                            new go.Binding("text", "name").makeTwoWay()
                        )
                    ) // end itemTemplate
                }
            ) // end Horizontal Panel
        ); // end Node

        let CustomLink: any = function(): any {
            go.Link.call(this);
        };

        // an orthogonal link template, reshapable and relinkable
        this.myDiagram.linkTemplate = $$(
            go.Link, // defined below
            {
                routing: go.Link.AvoidsNodes,
                corner: 4,
                curve: go.Link.JumpGap,
                reshapable: true,
                resegmentable: true,
                relinkableFrom: true,
                relinkableTo: true
            },
            new go.Binding("points").makeTwoWay(),
            $$(go.Shape, { stroke: "#2F4F4F", strokeWidth: 2 })
        );

        // support double-clicking in the background to add a copy of this data as a node
        this.myDiagram.toolManager.clickCreatingTool.archetypeNodeData = {
            name: "Unit",
            leftArray: [],
            rightArray: [],
            topArray: [],
            bottomArray: []
        };

        this.myDiagram.contextMenu = $$(
            go.Adornment,
            "Vertical",
            this.makeButton(
                "Paste",
                function(e, obj) {
                    e.diagram.commandHandler.pasteSelection(
                        e.diagram.lastInput.documentPoint
                    );
                },
                function(o) {
                    return o.diagram.commandHandler.canPasteSelection();
                }
            ),
            this.makeButton(
                "Undo",
                function(e, obj) {
                    e.diagram.commandHandler.undo();
                },
                function(o) {
                    return o.diagram.commandHandler.canUndo();
                }
            ),
            this.makeButton(
                "Redo",
                function(e, obj) {
                    e.diagram.commandHandler.redo();
                },
                function(o) {
                    return o.diagram.commandHandler.canRedo();
                }
            )
        );

        // load the diagram from JSON data
        this.load();

        // This custom-routing Link class tries to separate parallel links from each other.
        // This assumes that ports are lined up in a row/column on a side of the node.

        go.Diagram.inherit(CustomLink, go.Link);

        CustomLink.prototype.findSidePortIndexAndCount = function(node, port) {
            var nodedata = node.data;
            if (nodedata !== null) {
                var portdata = port.data;
                var side = port._side;
                var arr = nodedata[side + "Array"];
                var len = arr.length;
                for (var i = 0; i < len; i++) {
                    if (arr[i] === portdata) return [i, len];
                }
            }
            return [-1, len];
        };

        /** @override */
        CustomLink.prototype.computeEndSegmentLength = function(
            node,
            port,
            spot,
            from
        ) {
            // var esl = go.Link.prototype.computeEndSegmentLength.call(this, node, port, spot, from);
            var esl = this.__this.computeEndSegmentLength.call(
                this,
                node,
                port,
                spot,
                from
            );
            var other = this.getOtherPort(port);
            if (port !== null && other !== null) {
                var thispt = port.getDocumentPoint(this.computeSpot(from));
                var otherpt = other.getDocumentPoint(this.computeSpot(!from));
                if (
                    Math.abs(thispt.x - otherpt.x) > 20 ||
                    Math.abs(thispt.y - otherpt.y) > 20
                ) {
                    var info = this.findSidePortIndexAndCount(node, port);
                    var idx = info[0];
                    var count = info[1];
                    if (port._side == "top" || port._side == "bottom") {
                        if (otherpt.x < thispt.x) {
                            return esl + 4 + idx * 8;
                        } else {
                            return esl + (count - idx - 1) * 8;
                        }
                    } else {
                        // left or right
                        if (otherpt.y < thispt.y) {
                            return esl + 4 + idx * 8;
                        } else {
                            return esl + (count - idx - 1) * 8;
                        }
                    }
                }
            }
            return esl;
        };

        /** @override */
        CustomLink.prototype.hasCurviness = function() {
            if (isNaN(this.curviness)) return true;
            return this.__this.hasCurviness.call(this);
        };

        /** @override */
        CustomLink.prototype.computeCurviness = function() {
            if (isNaN(this.curviness)) {
                var fromnode = this.fromNode;
                var fromport = this.fromPort;
                var fromspot = this.computeSpot(true);
                var frompt = fromport.getDocumentPoint(fromspot);
                var tonode = this.toNode;
                var toport = this.toPort;
                var tospot = this.computeSpot(false);
                var topt = toport.getDocumentPoint(tospot);
                if (
                    Math.abs(frompt.x - topt.x) > 20 ||
                    Math.abs(frompt.y - topt.y) > 20
                ) {
                    if (
                        (fromspot.equals(go.Spot.Left) ||
                            fromspot.equals(go.Spot.Right)) &&
                        (tospot.equals(go.Spot.Left) ||
                            tospot.equals(go.Spot.Right))
                    ) {
                        var fromseglen = this.computeEndSegmentLength(
                            fromnode,
                            fromport,
                            fromspot,
                            true
                        );
                        var toseglen = this.computeEndSegmentLength(
                            tonode,
                            toport,
                            tospot,
                            false
                        );
                        var c = (fromseglen - toseglen) / 2;
                        if (frompt.x + fromseglen >= topt.x - toseglen) {
                            if (frompt.y < topt.y) return c;
                            if (frompt.y > topt.y) return -c;
                        }
                    } else if (
                        (fromspot.equals(go.Spot.Top) ||
                            fromspot.equals(go.Spot.Bottom)) &&
                        (tospot.equals(go.Spot.Top) ||
                            tospot.equals(go.Spot.Bottom))
                    ) {
                        var fromseglen = this.computeEndSegmentLength(
                            fromnode,
                            fromport,
                            fromspot,
                            true
                        );
                        var toseglen = this.computeEndSegmentLength(
                            tonode,
                            toport,
                            tospot,
                            false
                        );
                        var c = (fromseglen - toseglen) / 2;
                        if (frompt.x + fromseglen >= topt.x - toseglen) {
                            if (frompt.y < topt.y) return c;
                            if (frompt.y > topt.y) return -c;
                        }
                    }
                }
            }
            return this.__this.computeCurviness.call(this);
        };

        ////////////////////////////////////////////////////////
    }

    makeButton(text, action, visiblePredicate?) {
        return $$(
            "ContextMenuButton",
            $$(go.TextBlock, text),
            { click: action },
            // don't bother with binding GraphObject.visible if there's no predicate
            visiblePredicate
                ? new go.Binding("visible", "", function(o, e) {
                      return o.diagram ? visiblePredicate(o, e) : false;
                  }).ofObject()
                : {}
        );
    }

    addPort(side) {
        console.log("here on add port");
        this.myDiagram.startTransaction("addPort");
        let __this = this.myDiagram;
        this.myDiagram.selection.each(function(node) {
            console.log(1);
            if (!(node instanceof go.Node)) return;
            var i = 0;
            while (node.findPort(side + i.toString()) !== node) i++;
            var name = side + i.toString();
            var arr = node.data[side + "Array"];
            if (arr) {
                var newportdata = {
                    portId: name,
                    portColor: go.Brush.randomColor()
                };
                console.log(2);
                __this.model.insertArrayItem(arr, -1, newportdata);
            }
        });
        this.myDiagram = __this;
        this.myDiagram.commitTransaction("addPort");
    }

    removePort(port) {
        this.myDiagram.startTransaction("removePort");
        var pid = port.portId;
        var arr = port.panel.itemArray;
        for (var i = 0; i < arr.length; i++) {
            if (arr[i].portId === pid) {
                this.myDiagram.model.removeArrayItem(arr, i);
                break;
            }
        }
        this.myDiagram.commitTransaction("removePort");
    }

    removeAll(port) {
        this.myDiagram.startTransaction("removePorts");
        var nodedata = port.part.data;
        var side = port._side; // there are four property names, all ending in "Array"
        this.myDiagram.model.setDataProperty(nodedata, side + "Array", []); // an empty Array
        this.myDiagram.commitTransaction("removePorts");
    }

    changeColor(port) {
        this.myDiagram.startTransaction("colorPort");
        var data = port.data;
        this.myDiagram.model.setDataProperty(
            data,
            "portColor",
            go.Brush.randomColor()
        );
        this.myDiagram.commitTransaction("colorPort");
    }

    save() {
        console.log(this.myDiagram.model.toJson());
        console.log(new JsonToNetworkDiagramParser().parsJson(this.servers, this.myDiagram.model.toJson()))
        // document.getElementById("mySavedModel").value = this.myDiagram.model.toJson();
        // this.myDiagram.isModified = false;
    }

    load() {
        this.myDiagram.model = go.Model.fromJson(
            this.diagramJsonCreator(this.servers)
        );
        // this.myDiagram.model = go.Model.fromJson(document.getElementById("mySavedModel").value);
        // this.myDiagram.model = go.Model.fromJson({
        //     class: "go.GraphLinksModel",
        //     copiesArrays: true,
        //     copiesArrayObjects: true,
        //     linkFromPortIdProperty: "fromPort",
        //     linkToPortIdProperty: "toPort",
        //     nodeDataArray: [
        //         {
        //             source: this.samplePhysicalServer,
        //             describtion: "192.168.1.3",
        //             key: 1,
        //             name: "Server Fedora",
        //             loc: "50 50",
        //             leftArray: [],
        //             topArray: [],
        //             bottomArray: [
        //                 {
        //                     name: "eth0",
        //                     portColor: "#316571",
        //                     portId: "bottom0"
        //                 }
        //             ]
        //         },
        //         {
        //             source: this.samplePhysicalServer,
        //             describtion: "192.168.1.2",
        //             key: 2,
        //             name: "Server CentOS",
        //             loc: "200 284",
        //             leftArray: [],
        //             topArray: [],
        //             bottomArray: [
        //                 {
        //                     name: "eth0",
        //                     portColor: "#316571",
        //                     portId: "bottom0"
        //                 }
        //             ]
        //         },
        //         {
        //             source: this.samplePhysicalServer,
        //             describtion: "192.168.1.1",
        //             key: 3,
        //             name: "Server Ubuntu",
        //             loc: "400 50",
        //             leftArray: [],
        //             topArray: [],
        //             bottomArray: [
        //                 {
        //                     name: "eth0",
        //                     portColor: "#316571",
        //                     portId: "bottom0"
        //                 }
        //             ]
        //         }
        //     ],
        //     linkDataArray: []
        // });
    }

    diagramJsonCreator(servers: Server[]) {
        //This is for Networkcard
        let nodeDataArray = [];
        servers.forEach(server => {
            let bottomArray = [];
            let primeIp = "";
            server.networkCards.forEach(networkCard => {
                if (networkCard.isPrimary) {
                    primeIp = networkCard.ipAddress;
                }
                bottomArray.push({
                    name: networkCard.name + "\n" + networkCard.ipAddress,
                    portColor: "#316571",
                    portId: networkCard.name
                });
            });
            nodeDataArray.push({
                source: this.samplePhysicalServer,
                describtion: primeIp,
                key: server.name,
                name: server.name,
                loc: "400 50",
                bottomArray: bottomArray
            });
        });
        return {
            class: "go.GraphLinksModel",
            copiesArrays: true,
            copiesArrayObjects: true,
            linkFromPortIdProperty: "fromPort",
            linkToPortIdProperty: "toPort",
            nodeDataArray: nodeDataArray,
            linkDataArray: []
        };
    }
}
