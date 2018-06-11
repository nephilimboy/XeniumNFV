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
    selector: "accessory-controller",
    templateUrl: "./accessoryController.component.html"
})
export class AccessoryController extends go.Link implements OnInit, AfterViewInit {
    @ViewChild("plateControllerAccessory") plateControllerAccessory: ElementRef;

    public sampleOdl = require("../../../content/img/XenoNet/sampleOdl.png");


    public __this = this;

    constructor() {
        super();
    }

    ngAfterViewInit() {}

    ngOnInit() {
        ///////////////////////////////////////////////////////
        var plateControllerAccessory = $$(
            go.Palette,
            this.plateControllerAccessory.nativeElement.id
        );

        plateControllerAccessory.nodeTemplate = $$(
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
                    minSize: new go.Size(65, 75)
                }),

                $$(
                    go.Picture,

                    {
                        // margin: 20,
                        width: 60,
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
                        width: 65,
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
                        width: 65,
                        height: 110,
                        editable: true
                    },
                    new go.Binding("text", "describtion").makeTwoWay()
                )
            ) // end Auto Panel body
        );

        plateControllerAccessory.model.nodeDataArray = [
            {
                source: this.sampleOdl,
                key: 0,
                name: "Controller",
                describtion: "ODL",
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
        ];

        ////////////////////////////////////////////////////////
    }
}
