/*
*    @ AH.GHORAB
*/
import {
    Component,
    OnInit,
    Input,
    AfterViewInit,
    ViewChild,
    ElementRef
} from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { EventManager } from "ng-jhipster";
import { DomSanitizer } from "@angular/platform-browser";
import { Server } from "../../servers/server.model";

// declare var window:any;

@Component({
    selector: "xnet-serverStatus",
    template: `
        <div style="max-width: 39% !important; float: left; position: relative; margin-left: 5px ;" sa-widget [colorbutton]="false" [editbutton]="false" [togglebutton]="false" [deletebutton]="false" [fullscreenbutton]="false" color="blueDark">
      <header style="background-color: #16A085 !important;">
        <h2><strong>{{server.name}}</strong></h2>
      </header>
      <!-- widget div-->
      <div>
        <!-- widget content -->
        <div class="widget-body">

          <fieldset>
            <section>
            
               <div class="well">
                <ul class="list-inline">
                  <li style="text-align: center;">&nbsp;&nbsp;&nbsp;
                    <div  saEasyPieChartContainer class="easy-pie-chart easyPieChart"  [dataPercent]="server.cpuUsage"  data-pie-size="70">
                      <span class="percent percent-sign " style="font-size: 20px !important;"></span>
                      <span style="font-size: 10px !important; color: black;display: block;font-weight: 700;position: absolute;top: 100%;width: 100%;text-align: center;">CPU</span>
                    </div>
                    &nbsp;&nbsp;&nbsp;
                  </li>

                  <li style="text-align: center;">&nbsp;&nbsp;&nbsp;
                    <div saEasyPieChartContainer class="easy-pie-chart easyPieChart"  [dataPercent]="server.ramUsage" data-pie-size="70">
                      <span class="percent percent-sign  " style="font-size: 20px !important;"></span>
                      <span style="font-size: 10px !important; color: black;display: block;font-weight: 700;position: absolute;top: 100%;width: 100%;text-align: center;">RAM</span>
                    </div>
                    &nbsp;&nbsp;&nbsp;
                  </li>

                  <li style="text-align: center;">&nbsp;&nbsp;&nbsp;
                    <div saEasyPieChartContainer class="easy-pie-chart easyPieChart"  [dataPercent]="server.hhdUsage" data-pie-size="70">
                      <span class="percent percent-sign " style="font-size: 20px !important;">36</span>
                      <span style="font-size: 10px !important; color: black;display: block;font-weight: 700;position: absolute;top: 100%;width: 100%;text-align: center;">HDD</span>
                    </div>
                     
                    &nbsp;&nbsp;&nbsp;
                  </li>

                </ul>

              </div>
            </section>
          </fieldset>

           <fieldset>
            <section>
              <legend>Server Status</legend>
                  <div class="btn btn-info btn-xs"  style="cursor: pointer; margin-top: -45px;float: right; background-color: #16a085;border-color: #16a085;" (click)="refresh()">
                    <i class="button-icon fa fa-refresh"></i>
                  </div>
                <div class="table-responsive dataTables_wrapper form-inline dt-bootstrap no-footer">
                  <table class="table table-striped table-bordered">

                    <thead>
                      <tr>
                        
                      </tr>
                    </thead>
                    <tbody>
                    </tbody>
                      <tr>
                        <td>
                          Linux
                        </td>
                        <td>
                        {{server.os}}
                        </td>
                      </tr>
                      <tr>
                        <td>
                          IP Address
                        </td>
                        <td>
                          {{PrimeServerIp}}
                        </td>
                      </tr>

                      <tr>
                        <td>
                          Docker
                        </td>
                        <td>
                          {{server.dockerVersion}}
                        </td>
                      </tr>

                      <tr>
                        <td>
                          OVS
                        </td>
                        <td>
                          {{server.ovsVersion}}
                        </td>
                      </tr>

                      <tr>
                        <td>
                          KVM
                        </td>
                        <td>
                        {{server.kvmVersion}}
                        </td>
                      </tr>


                  </table>
                </div>
            </section>
           </fieldset>

        </div>
        <!-- end widget content -->
      </div>
      <!-- end widget div -->
    </div>
    `
})
export class ServerStatusComponent implements OnInit, AfterViewInit {
    @Input() server: Server;

    public PrimeServerIp: string;

    constructor() {}

    ngOnInit() {
        this.server.networkCards.forEach(el => {
          console.log(el.isPrimary);
            if (el.isPrimary) {
                this.PrimeServerIp = el.ipAddress;
            }
        });
    }

    secureRes() {}

    ngAfterViewInit() {
    }

    refresh(){
    }
}
