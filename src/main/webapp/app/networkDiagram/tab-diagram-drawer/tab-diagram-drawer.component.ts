import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {Server} from '../../servers/server.model';
import * as go from 'gojs';
import {NetworkDiagramComponentProperties} from '../networkDiagramEntityProperties/networkDiagramComponentProperties.model';
import {JsonToNetworkDiagramParser} from '../netWorkDiagramParser.util';
import {NetworkDiagramService} from '../networkDiagram.service';
import {ResponseWrapper} from '../../shared/model/response-wrapper.model';
import {NotificationService} from '../../shared/utils/notification.service';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';

const $$ = go.GraphObject.make;
const uuidv4 = require('uuid/v4');
const FileSaver = require('file-saver');

@Component({
    selector: 'diagram-drawer',
    templateUrl: './tab-diagram-drawer.component.html',
    styleUrls: ['../utils/DataInspector.css']
})
export class TabDiagramDrawer extends go.Link implements OnInit, AfterViewInit {
    @Input() server: Server;
    public networkDiagramComponentProperties: NetworkDiagramComponentProperties = new NetworkDiagramComponentProperties();
    public myDiagram: any;
    public __this = this;
    private selectedPart: any;
    private ipCounter = 0;

    constructor(private networkDiagramService: NetworkDiagramService,
                private notificationService: NotificationService,
                private ng4LoadingSpinnerService: Ng4LoadingSpinnerService) {
        super();
    }

    ngOnInit() {
    }

    ngAfterViewInit() {
        ///////////////////////////////////////////////////////

        //for conciseness in defining node templates

        this.myDiagram = $$(
            go.Diagram,
            // this.tabdiagramDrawer.nativeElement.id, //Diagram refers to its DIV HTML element by id
            document.getElementById(this.server.name),
            {
                initialContentAlignment: go.Spot.Center,
                'undoManager.isEnabled': true,
                allowDrop: true
            }
        );

        this.myDiagram.addDiagramListener('ObjectSingleClicked', e => {
            var part = e.subject.part;
            if (!(part instanceof go.Link)) {
                console.log('my part IS');
                console.log(part.data);
                this.selectedPart = part;
                if (part.data.name != 'Switch') {
                    this.networkDiagramComponentProperties.name =
                        part.data.describtion;
                    this.networkDiagramComponentProperties.ipAddress =
                        part.data.ipAddress;
                    this.networkDiagramComponentProperties.cpu = part.data.cpu;
                    this.networkDiagramComponentProperties.ram = part.data.ram;
                    this.networkDiagramComponentProperties.isDedicatedRes =
                        part.data.isDedicatedRes == '1' ? true : false;
                } else {
                    this.networkDiagramComponentProperties.name = '';
                    this.networkDiagramComponentProperties.ipAddress = '';
                    this.networkDiagramComponentProperties.cpu = '0';
                    this.networkDiagramComponentProperties.ram = '0';
                    this.networkDiagramComponentProperties.isDedicatedRes = false;
                }
            }
        });

        this.myDiagram.addDiagramListener('BackgroundSingleClicked', e => {
            this.networkDiagramComponentProperties.name = '';
            this.networkDiagramComponentProperties.ipAddress = '';
            this.networkDiagramComponentProperties.cpu = '0';
            this.networkDiagramComponentProperties.ram = '0';
            this.networkDiagramComponentProperties.isDedicatedRes = false;
        });

        this.myDiagram.addDiagramListener('ExternalObjectsDropped', (e)=> {
            e.subject.each((node)=> {
                var model = e.diagram.model;
                var rand = uuidv4();
                if (node.data.name === 'Switch') {
                    model.setDataProperty(node.data, 'key', 'Switch#' + rand);
                    model.setDataProperty(node.data, 'describtion', 'S#' + rand.split('-')[1]);
                }
                else if (node.data.name === 'VNF') {
                    this.ipCounter++;
                    model.setDataProperty(node.data, 'key', 'VNF#' + rand);
                    model.setDataProperty(node.data, 'describtion', 'V#' + rand.split('-')[1]);
                    model.setDataProperty(node.data, 'ipAddress', '192.168.10.' + this.ipCounter)
                }
                else if (node.data.name === 'Controller') {
                    model.setDataProperty(node.data, 'key', 'Controller#' + rand);
                    model.setDataProperty(node.data, 'describtion', 'C#' + rand.split('-')[1]);
                }
            });
        });


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
            'Vertical',
            this.makeButton('Copy', (e, obj) => {
                e.diagram.commandHandler.copySelection();
            }),
            this.makeButton('Delete', (e, obj) => {
                e.diagram.commandHandler.deleteSelection();
            }),
            $$(go.Shape, 'LineH', {
                strokeWidth: 2,
                height: 1,
                stretch: go.GraphObject.Horizontal
            }),
            this.makeButton('Add top port', (e, obj) => {
                this.addPort('top');
            }),
            this.makeButton('Add left port', (e, obj) => {
                this.addPort('left');
            }),
            this.makeButton('Add right port', (e, obj) => {
                this.addPort('right');
            }),
            this.makeButton('Add bottom port', (e, obj) => {
                this.addPort('bottom');
            })
        );

        var portSize = new go.Size(8, 8);

        var portMenu = $$(
            // context menu for each port
            go.Adornment,
            'Vertical',
            this.makeButton(
                'Remove port',
                // in the click event handler, the obj.part is the Adornment;
                // its adornedObject is the port
                function(e, obj) {
                    this.removePort(obj.part.adornedObject);
                }
            ),
            this.makeButton('Change color', (e, obj) => {
                this.changeColor(obj.part.adornedObject);
            }),
            this.makeButton('Remove side ports', (e, obj) => {
                this.removeAll(obj.part.adornedObject);
            })
        );

        // the node template
        // includes a panel on each side with an itemArray of panels containing ports
        this.myDiagram.nodeTemplate = $$(
            go.Node,
            'Table',
            {
                locationObjectName: 'BODY',
                locationSpot: go.Spot.Center,
                selectionObjectName: 'BODY',
                contextMenu: nodeMenu
            },
            new go.Binding('location', 'loc', go.Point.parse).makeTwoWay(
                go.Point.stringify
            ),

            // the body
            $$(
                go.Panel,
                'Auto',
                {
                    row: 1,
                    column: 1,
                    name: 'BODY',
                    stretch: go.GraphObject.Fill
                },
                $$(go.Shape, 'Rectangle', {
                    fill: '#ffffff',
                    strokeWidth: 1,
                    minSize: new go.Size(85, 110)
                }),

                $$(
                    go.Picture,

                    {
                        // margin: 20,
                        width: 58,
                        height: 70
                    },
                    new go.Binding('source')
                ),
                $$(
                    go.TextBlock,
                    {
                        margin: 2,
                        verticalAlignment: go.Spot.Top,
                        font: '14px  Segoe UI,sans-serif',
                        stroke: 'black',
                        textAlign: 'center',
                        width: 65,
                        height: 110,
                        editable: true
                    },
                    new go.Binding('text', 'name').makeTwoWay()
                ),
                $$(
                    go.TextBlock,
                    {
                        margin: 2,
                        verticalAlignment: go.Spot.Bottom,
                        font: '14px  Segoe UI,sans-serif',
                        stroke: 'black',
                        textAlign: 'center',
                        width: 65,
                        height: 110,
                        editable: false
                    },
                    new go.Binding('text', 'describtion').makeTwoWay()
                ),
                $$(
                    go.TextBlock,
                    {
                        margin: 2,
                        verticalAlignment: go.Spot.Bottom,
                        font: '11px  Segoe UI,sans-serif',
                        stroke: 'blue',
                        textAlign: 'center',
                        width: 84,
                        height: 135,
                        editable: false
                    },
                    new go.Binding('text', 'ipAddress').makeTwoWay()
                )
            ),

            // end Auto Panel body

            // the Panel holding the left port elements, which are themselves Panels,
            // created for each item in the itemArray, bound to data.leftArray
            $$(go.Panel, 'Vertical', new go.Binding('itemArray', 'leftArray'), {
                row: 1,
                column: 0,
                itemTemplate: $$(
                    go.Panel,
                    {
                        _side: 'left', // internal property to make it easier to tell which side it's on
                        fromSpot: go.Spot.Left,
                        toSpot: go.Spot.Left,
                        fromLinkable: true,
                        toLinkable: true,
                        cursor: 'pointer'
                        // contextMenu: portMenu
                    },
                    new go.Binding('portId', 'portId'),
                    $$(
                        go.Shape,
                        'Rectangle',
                        {
                            stroke: null,
                            strokeWidth: 0,
                            desiredSize: portSize,
                            margin: new go.Margin(1, 0)
                        },
                        new go.Binding('fill', 'portColor')
                    )
                ) // end itemTemplate
            }), // end Vertical Panel

            // the Panel holding the top port elements, which are themselves Panels,
            // created for each item in the itemArray, bound to data.topArray
            $$(
                go.Panel,
                'Horizontal',
                new go.Binding('itemArray', 'topArray'),
                {
                    row: 0,
                    column: 1,
                    itemTemplate: $$(
                        go.Panel,
                        {
                            _side: 'top',
                            fromSpot: go.Spot.Top,
                            toSpot: go.Spot.Top,
                            fromLinkable: true,
                            toLinkable: true,
                            cursor: 'pointer'
                            // contextMenu: portMenu
                        },
                        new go.Binding('portId', 'portId'),
                        $$(
                            go.Shape,
                            'Rectangle',
                            {
                                stroke: null,
                                strokeWidth: 0,
                                desiredSize: portSize,
                                margin: new go.Margin(0, 1)
                            },
                            new go.Binding('fill', 'portColor')
                        )
                    ) // end itemTemplate
                }
            ), // end Horizontal Panel

            // the Panel holding the right port elements, which are themselves Panels,
            // created for each item in the itemArray, bound to data.rightArray
            $$(
                go.Panel,
                'Vertical',
                new go.Binding('itemArray', 'rightArray'),
                {
                    row: 1,
                    column: 2,
                    itemTemplate: $$(
                        go.Panel,
                        {
                            _side: 'right',
                            fromSpot: go.Spot.Right,
                            toSpot: go.Spot.Right,
                            fromLinkable: true,
                            toLinkable: true,
                            cursor: 'pointer',
                            contextMenu: portMenu
                        },
                        new go.Binding('portId', 'portId'),
                        $$(
                            go.Shape,
                            'Rectangle',
                            {
                                stroke: null,
                                strokeWidth: 0,
                                desiredSize: portSize,
                                margin: new go.Margin(1, 0)
                            },
                            new go.Binding('fill', 'portColor')
                        )
                    ) // end itemTemplate
                }
            ), // end Vertical Panel

            // the Panel holding the bottom port elements, which are themselves Panels,
            // created for each item in the itemArray, bound to data.bottomArray
            $$(
                go.Panel,
                'Horizontal',
                new go.Binding('itemArray', 'bottomArray'),
                {
                    row: 2,
                    column: 1,
                    itemTemplate: $$(
                        go.Panel,
                        {
                            _side: 'bottom',
                            fromSpot: go.Spot.Bottom,
                            toSpot: go.Spot.Bottom,
                            fromLinkable: true,
                            toLinkable: true,
                            cursor: 'pointer'
                            // contextMenu: portMenu
                        },
                        new go.Binding('portId', 'portId'),
                        $$(
                            go.Shape,
                            'Rectangle',
                            {
                                stroke: null,
                                strokeWidth: 0,
                                desiredSize: portSize,
                                margin: new go.Margin(0, 1)
                            },
                            new go.Binding('fill', 'portColor')
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
            // ParallelRouteLink,
            {
                // curve: go.Link.Bezier,
                routing: go.Link.AvoidsNodes,
                corner: 4,
                isTreeLink: true,
                curve: go.Link.JumpGap,
                reshapable: true,
                resegmentable: true,
                relinkableFrom: true,
                relinkableTo: true
            },
            new go.Binding('points').makeTwoWay(),

            $$(go.Shape, {stroke: '#2F4F4F', strokeWidth: 2})
        );

        // support double-clicking in the background to add a copy of this data as a node
        this.myDiagram.toolManager.clickCreatingTool.archetypeNodeData = {
            name: 'Unit',
            leftArray: [],
            rightArray: [],
            topArray: [],
            bottomArray: []
        };

        this.myDiagram.contextMenu = $$(
            go.Adornment,
            'Vertical',
            this.makeButton(
                'Paste',
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
                'Undo',
                function(e, obj) {
                    e.diagram.commandHandler.undo();
                },
                function(o) {
                    return o.diagram.commandHandler.canUndo();
                }
            ),
            this.makeButton(
                'Redo',
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
                var arr = nodedata[side + 'Array'];
                var len = arr.length;
                for (var i = 0; i < len; i++) {
                    if (arr[i] === portdata) return [i, len];
                }
            }
            return [-1, len];
        };

        /** @override */
        CustomLink.prototype.computeEndSegmentLength = function(node,
                                                                port,
                                                                spot,
                                                                from) {
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
                    if (port._side == 'top' || port._side == 'bottom') {
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

    makeButton(text, action, visiblePredicate ?) {
        return $$(
            'ContextMenuButton',
            $$(go.TextBlock, text),
            {click: action},
            // don't bother with binding GraphObject.visible if there's no predicate
            visiblePredicate
                ? new go.Binding('visible', '', function(o, e) {
                    return o.diagram ? visiblePredicate(o, e) : false;
                }).ofObject()
                : {}
        );
    }

    addPort(side) {
        console.log('here on add port');
        this.myDiagram.startTransaction('addPort');
        let __this = this.myDiagram;
        this.myDiagram.selection.each(function(node) {
            console.log(1);
            if (!(node instanceof go.Node)) return;
            var i = 0;
            while (node.findPort(side + i.toString()) !== node) i++;
            var name = side + i.toString();
            var arr = node.data[side + 'Array'];
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
        this.myDiagram.commitTransaction('addPort');
    }

    removePort(port) {
        this.myDiagram.startTransaction('removePort');
        var pid = port.portId;
        var arr = port.panel.itemArray;
        for (var i = 0; i < arr.length; i++) {
            if (arr[i].portId === pid) {
                this.myDiagram.model.removeArrayItem(arr, i);
                break;
            }
        }
        this.myDiagram.commitTransaction('removePort');
    }

    removeAll(port) {
        this.myDiagram.startTransaction('removePorts');
        var nodedata = port.part.data;
        var side = port._side; // there are four property names, all ending in "Array"
        this.myDiagram.model.setDataProperty(nodedata, side + 'Array', []); // an empty Array
        this.myDiagram.commitTransaction('removePorts');
    }

    changeColor(port) {
        this.myDiagram.startTransaction('colorPort');
        var data = port.data;
        this.myDiagram.model.setDataProperty(
            data,
            'portColor',
            go.Brush.randomColor()
        );
        this.myDiagram.commitTransaction('colorPort');
    }


    load() {
        // this.myDiagram.model = go.Model.fromJson(document.getElementById("mySavedModel").value);
        this.myDiagram.model = go.Model.fromJson({
            class: 'go.GraphLinksModel',
            copiesArrays: true,
            copiesArrayObjects: true,
            linkFromPortIdProperty: 'fromPort',
            linkToPortIdProperty: 'toPort',
            nodeDataArray: [
                // {
                //   key: 1,
                //   name: "unit One",
                //   loc: "101 204",
                //   leftArray: [{ portColor: "#425e5c", portId: "left0" }],
                //   topArray: [{ portColor: "#d488a2", portId: "top0" }],
                //   bottomArray: [{ portColor: "#316571", portId: "bottom0" }],
                //   rightArray: [
                //     { portColor: "#923951", portId: "right0" },
                //     { portColor: "#ef3768", portId: "right1" }
                //   ]
                // },
                // {
                //   key: 2,
                //   name: "unit Two",
                //   loc: "320 152",
                //   leftArray: [
                //     { portColor: "#7d4bd6", portId: "left0" },
                //     { portColor: "#cc585c", portId: "left1" },
                //     { portColor: "#b1273a", portId: "left2" }
                //   ],
                //   topArray: [{ portColor: "#14abef", portId: "top0" }],
                //   bottomArray: [
                //     { portColor: "#dd45c7", portId: "bottom0" },
                //     { portColor: "#995aa6", portId: "bottom1" },
                //     { portColor: "#6b95cb", portId: "bottom2" }
                //   ],
                //   rightArray: []
                // },
                // {
                //   key: 3,
                //   name: "unit Three",
                //   loc: "384 319",
                //   leftArray: [
                //     { portColor: "#bd8f27", portId: "left0" },
                //     { portColor: "#c14617", portId: "left1" },
                //     { portColor: "#47fa60", portId: "left2" }
                //   ],
                //   topArray: [{ portColor: "#d08154", portId: "top0" }],
                //   bottomArray: [{ portColor: "#6cafdb", portId: "bottom0" }],
                //   rightArray: []
                // },
                // {
                //   key: 4,
                //   name: "unit Four",
                //   loc: "138 351",
                //   leftArray: [{ portColor: "#491389", portId: "left0" }],
                //   topArray: [{ portColor: "#77ac1e", portId: "top0" }],
                //   bottomArray: [{ portColor: "#e9701b", portId: "bottom0" }],
                //   rightArray: [
                //     { portColor: "#24d05e", portId: "right0" },
                //     { portColor: "#cfabaa", portId: "right1" }
                //   ]
                // }
            ],
            linkDataArray: [
                // { from: 4, to: 2, fromPort: "top0", toPort: "bottom0" },
                // { from: 4, to: 2, fromPort: "top0", toPort: "bottom0" },
                // { from: 3, to: 2, fromPort: "top0", toPort: "bottom1" },
                // { from: 4, to: 3, fromPort: "right0", toPort: "left0" },
                // { from: 4, to: 3, fromPort: "right1", toPort: "left2" },
                // { from: 1, to: 2, fromPort: "right0", toPort: "left1" },
                // { from: 1, to: 2, fromPort: "right1", toPort: "left2" }
            ]
        });
    }

    applyChangeToComponent(networkDiagramComponentProperties: NetworkDiagramComponentProperties) {
        if (this.selectedPart) {
            this.myDiagram.startTransaction('set all properties');
            console.log('set all properties');
            this.myDiagram.model.setDataProperty(
                this.selectedPart.data,
                'describtion',
                networkDiagramComponentProperties.name
            );
            this.myDiagram.model.setDataProperty(
                this.selectedPart.data,
                'ipAddress',
                networkDiagramComponentProperties.ipAddress
            );
            this.myDiagram.model.setDataProperty(
                this.selectedPart.data,
                'cpu',
                Math.floor(
                    Number(networkDiagramComponentProperties.cpu)
                ).toString()
            );
            this.myDiagram.model.setDataProperty(
                this.selectedPart.data,
                'ram',
                Math.floor(
                    Number(networkDiagramComponentProperties.ram)
                ).toString()
            );
            this.myDiagram.model.setDataProperty(
                this.selectedPart.data,
                'isDedicatedRes',
                networkDiagramComponentProperties.isDedicatedRes == true
                    ? '1'
                    : '0'
            );
            this.myDiagram.commitTransaction('set all properties');
        }
    }

    zoomIn() {
        this.myDiagram.commandHandler.increaseZoom()
    }


    zoomOut() {
        this.myDiagram.commandHandler.decreaseZoom()
    }

    fitToView() {
        this.myDiagram.zoomToRect(this.myDiagram.documentBounds)
    }

    undo() {
        this.myDiagram.commandHandler.undo()
    }

    redo() {
        this.myDiagram.commandHandler.redo()
    }

    deployDiagram() {
        console.log(this.myDiagram.model.toJson());
        console.log(new JsonToNetworkDiagramParser().convertDiagramJsonToTopology(this.myDiagram.model.toJson()));
        this.ng4LoadingSpinnerService.show();
        this.networkDiagramService.create('/new', new JsonToNetworkDiagramParser().convertDiagramJsonToTopology(this.myDiagram.model.toJson())).subscribe((res: any) => {
            this.ng4LoadingSpinnerService.hide();
            this.notificationService.smallBox({
                title: "Topology Created successfully",
                content: "<i class='fa fa-clock-o'></i> <i>2 seconds ago...</i>",
                color: "#16A085",
                iconSmall: "fa fa-check bounce animated",
                timeout: 4000
            });
        });

    }

    saveDiagram() {
        console.log('here  on save');
        var blob = new Blob([this.myDiagram.model.toJson()], {type: 'text/plain'});
        FileSaver.saveAs(blob, this.server.name + '.xnet');
    }

    clearDiagram() {
        this.myDiagram.clear();
        this.ipCounter = 0;
    }

    loadDiagram(event) {
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            let file: File = fileList[0];
            let formData: FormData = new FormData();
            formData.append('uploadFile', file, file.name);
            console.log(file);
            console.log(formData);
            let reader = new FileReader();
            reader.onloadend = (e) => {
                this.clearDiagram();
                this.myDiagram.model = go.Model.fromJson(reader.result);
                //Find Max Ip Address of loaded topology and replace it with current one
                let maxIpAddress: number = 0;
                this.myDiagram.nodes.each((node)=> {
                    if(node.data.name === 'VNF'){
                        if(maxIpAddress < Number((node.data.ipAddress).split('.')[3])) {
                            maxIpAddress = Number((node.data.ipAddress).split('.')[3]);
                        }
                    }
                });
                this.ipCounter = maxIpAddress;
            };
            reader.readAsText(file);
        }
    }

    removeDiagram() {
        this.notificationService.smartMessageBox(
            {
                title:
                '<i class=\'fa fa-sign-out txt-color-orangeDark\' style=\'font-family: \'IranSansNormal\' !important;font-size: 9px;\'></i> Remove Deployed Diagram From Current Server <span class=\'txt-color-orangeDark\'><strong>' +
                $('#show-shortcut').text() +
                '</strong></span> ?',
                content: '',
                buttons: '[No][Yes]'
            },
            ButtonPressed => {
                if (ButtonPressed == 'Yes') {
                    this.ng4LoadingSpinnerService.show();
                    this.networkDiagramService.removeDiagram('/deleteTopo/', this.server.id).subscribe((res: any) => {
                        console.log('Topology Deleted');
                        this.ng4LoadingSpinnerService.hide();
                        this.notificationService.smallBox({
                            title: "Topology Delete successfully",
                            content: "<i class='fa fa-clock-o'></i> <i>2 seconds ago...</i>",
                            color: "#16A085",
                            iconSmall: "fa fa-check bounce animated",
                            timeout: 4000
                        });
                    });
                }
            }
        );
    }

}
