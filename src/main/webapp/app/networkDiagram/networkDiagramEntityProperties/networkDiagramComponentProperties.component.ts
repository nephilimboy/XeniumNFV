import { Component, Input, EventEmitter, Output } from "@angular/core";
import { OnInit } from "@angular/core/src/metadata/lifecycle_hooks";
import { NetworkDiagramComponentProperties } from "./networkDiagramComponentProperties.model";

@Component({
    selector: "Xnet-networkDiagramEntityPropertiesComponent",
    styles: [],
    templateUrl: "./networkDiagramComponentProperties.component.html"
})
export class NetworkDiagramComponentPropertiesComponent {
    @Input()
    networkDiagramComponentProperties: NetworkDiagramComponentProperties;
    @Output() applyChangeToComponent = new EventEmitter();

    constructor() {}

    applyChange() {
        this.applyChangeToComponent.emit(this.networkDiagramComponentProperties);
    }

    recievCpuFromKnob(cpu){
        this.networkDiagramComponentProperties.cpu = cpu;
    }
    recievRamFromKnob(ram){
        this.networkDiagramComponentProperties.ram = ram;
    }
}
