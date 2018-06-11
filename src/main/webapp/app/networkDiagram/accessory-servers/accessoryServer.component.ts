import {
    Component,
    OnInit,
    ViewChild,
    ElementRef,
    AfterViewInit
} from "@angular/core";

import * as go from "gojs";

const $$ = go.GraphObject.make;
@Component({
    selector: "accessory-server",
    templateUrl: "./accessoryServer.component.html"
})
export class AccessoryServer extends go.Link implements OnInit, AfterViewInit {
    @ViewChild("plateServerAccessory") plateServerAccessory: ElementRef;

    public sampleServer = require("../../../content/img/XenoNet/sampleServer.png");

    public sampleHost = require("../../../content/img/XenoNet/sampleHost.png");

    public __this = this;

    constructor() {
        super();
    }

    ngAfterViewInit() {}

    ngOnInit() {
        ///////////////////////////////////////////////////////
        var plateServerAccessory = $$(
            go.Palette,
            this.plateServerAccessory.nativeElement.id
        );

        plateServerAccessory.nodeTemplate = $$(
            go.Node,
            "Horizontal",

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
                    minSize: new go.Size(60, 75)
                }),

                $$(
                    go.Picture,

                    {
                        // margin: 20,
                        width: 58,
                        height: 70
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
                        width: 60,
                        height: 110,
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
                        width: 60,
                        height: 110,
                        editable: true
                    },
                    new go.Binding("text", "describtion").makeTwoWay()
                )
            ) // end Auto Panel body
        );

        plateServerAccessory.model.nodeDataArray = [
            {
                source: this.sampleServer,
                key: 0,
                name: "Server",
                describtion: "Tomcat",
                loc: "0 0",
                ipAddress: "",
                cpu: "0",
                ram: "0",
                isDedicatedRes: "0",
                leftArray: [],
                topArray: [],
                bottomArray: [{ portColor: "#316571", portId: "bottom0" }],
                rightArray: []
            },
            {
                source: this.sampleHost,
                key: 1,
                name: "Host",
                describtion: "User",
                loc: "0 0",
                ipAddress: "",
                cpu: "0",
                ram: "0",
                isDedicatedRes: "0",
                leftArray: [],
                topArray: [],
                bottomArray: [{ portColor: "#316571", portId: "bottom0" }],
                rightArray: []
            }
        ];

        ////////////////////////////////////////////////////////
    }
}
