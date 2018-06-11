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
  selector: "accessory-vnf",
  templateUrl: "./accessoryVnf.component.html"
})
export class AccessoryVnf extends go.Link implements OnInit, AfterViewInit {
  @ViewChild("plateVnfAccessory") plateVnfAccessory: ElementRef;

  public __this = this;
  public sampleVnf = require("../../../content/img/XenoNet/sampleVnf.png");

  constructor() {
    super();
  }

  ngAfterViewInit() {}

  ngOnInit() {
    ///////////////////////////////////////////////////////
    var plateVnfAccessory = $$(
      go.Palette,
      this.plateVnfAccessory.nativeElement.id
    );

    plateVnfAccessory.nodeTemplate = $$(
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
          minSize: new go.Size(60,75)
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
    plateVnfAccessory.model.nodeDataArray = [
      {
        source: this.sampleVnf,
        key: "VNF",
        name: "VNF",
        describtion: "BVnf",
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
