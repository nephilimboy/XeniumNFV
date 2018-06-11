import {
    Directive,
    Input,
    ElementRef,
    Output,
    EventEmitter
} from "@angular/core";

declare var $: any;

@Directive({
    selector: "[saKnob]"
})
export class KnobDirective {
    @Input() size: any;
    @Output() returnVal = new EventEmitter();
    constructor(private el: ElementRef) {
        System.import("jquery-knob").then(() => {
            this.render();
        });
    }

    render() {
        $(this.el.nativeElement).knob({
            width: this.size,
            height: this.size,
            displayinput: true,
            displayprevious: true,
            fgColor: "#16A085",
            change: (v)=> {
                this.returnVal.emit(v);
            }
        });
    }

}
