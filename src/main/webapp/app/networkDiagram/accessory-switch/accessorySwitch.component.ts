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
  selector: "accessory-switch",
  templateUrl: "./accessorySwitch.component.html"
})
export class AccessorySwitch extends go.Link implements OnInit, AfterViewInit {
  @ViewChild("plateSwitchAccessory") plateSwitchAccessory: ElementRef;

  public __this = this;
  public sampleSwitch = require("../../../content/img/XenoNet/sampleSwitch.png");

  constructor() {
    super();
  }

  ngAfterViewInit() {}

  ngOnInit() {
    ///////////////////////////////////////////////////////
    var plateSwitchAccessory = $$(
      go.Palette,
      this.plateSwitchAccessory.nativeElement.id
    );

    plateSwitchAccessory.nodeTemplate = $$(
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


    plateSwitchAccessory.model.nodeDataArray = [
      {
        source: this.sampleSwitch,
        key: "SWITCH",
        name: "Switch",
        describtion: "OVS",
        loc: "0 0",
        leftArray: [],
        topArray: [],
        bottomArray: [{ portColor: "#316571", portId: "bottom0" }],
        rightArray: []
      }
    ];

    ////////////////////////////////////////////////////////
  }
}
