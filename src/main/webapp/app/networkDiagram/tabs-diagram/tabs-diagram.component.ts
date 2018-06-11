/**
 * The main component that renders single TabComponent
 * instances.
 */

import {
  Component,
  ContentChildren,
  QueryList,
  AfterContentInit,
  ViewChild,
  ComponentFactoryResolver,
  ViewContainerRef
} from "@angular/core";

import { TabComponent } from "./tab-diagram.component";
import { DynamicTabsDirective } from "./dynamic-tabs.directive";

@Component({
  selector: "tabs-diagram",
  template: `

  <ul class="nav nav-tabs">
      <li *ngFor="let tab of staticTabs" [class.active]="tab.active">
          <a (click)="selectTab(tab)" >
              <i class="fa fa-lg fa-arrow-circle-o-down"></i>
              <span class="hidden-mobile hidden-tablet"> {{tab.title}} </span>
          </a>
      </li>
      <li *ngFor="let dynatab of dynamicTabs" [class.active]="dynatab.active">
          <a (click)="selectTab(dynatab)" >
              <i class="fa fa-lg fa-arrow-circle-o-down"></i>
              <span class="hidden-mobile hidden-tablet"> {{dynatab.title}} </span>
          </a>
      </li>
  </ul>

 
    
      <ng-content></ng-content>
      <ng-template dynamic-tabs #container></ng-template>
  
  `
})
export class TabsComponent implements AfterContentInit {
  dynamicTabs: TabComponent[] = [];

  @ContentChildren(TabComponent) staticTabs: QueryList<TabComponent>;

  @ViewChild(DynamicTabsDirective) dynamicTabPlaceholder: DynamicTabsDirective;

  /*
    Alternative approach of using an anchor directive
    would be to simply get hold of a template variable
    as follows
  */
  // @ViewChild('container', {read: ViewContainerRef}) dynamicTabPlaceholder;

  constructor(private _componentFactoryResolver: ComponentFactoryResolver) {}

  // contentChildren are set
  ngAfterContentInit() {
    // get all active tabs
    let activeTabs = this.staticTabs.filter(tab => tab.active);

    // if there is no active tab set, activate the first
    if (activeTabs.length === 0) {
      this.selectTab(this.staticTabs.first);
    }
  }

  openTab(title: string, template, data, isCloseable = false) {
    // get a component factory for our TabComponent
    let componentFactory = this._componentFactoryResolver.resolveComponentFactory(TabComponent);

    // fetch the view container reference from our anchor directive
    let viewContainerRef = this.dynamicTabPlaceholder.viewContainer;

    // alternatively...
    // let viewContainerRef = this.dynamicTabPlaceholder;

    // create a component instance
    let componentRef = viewContainerRef.createComponent(componentFactory);

    // set the according properties on our component instance
    let instance: TabComponent = componentRef.instance as TabComponent;
    instance.title = title;
    instance.template = template;
    instance.dataContext = data;
    instance.isCloseable = isCloseable;

    // remember the dynamic component for rendering the
    // tab navigation headers
    this.dynamicTabs.push(componentRef.instance as TabComponent);

    // set it active
    // this.selectTab(this.dynamicTabs[this.dynamicTabs.length - 1]);
  }

  selectTab(tab: TabComponent) {
    // deactivate all tabs
    this.staticTabs.toArray().forEach(tab => (tab.active = false));
    this.dynamicTabs.forEach(tab => (tab.active = false));

    // activate the tab the user has clicked on.
    tab.active = true;
  }

  closeTab(tab: TabComponent) {
    for (let i = 0; i < this.dynamicTabs.length; i++) {
      if (this.dynamicTabs[i] === tab) {
        // remove the tab from our array
        this.dynamicTabs.splice(i, 1);

        // destroy our dynamically created component again
        let viewContainerRef = this.dynamicTabPlaceholder.viewContainer;
        // let viewContainerRef = this.dynamicTabPlaceholder;
        viewContainerRef.remove(i);

        // set tab index to 1st one
        this.selectTab(this.staticTabs.first);
        break;
      }
    }
  }

  closeActiveTab() {
    let activeTabs = this.dynamicTabs.filter(tab => tab.active);
    if (activeTabs.length > 0) {
      // close the 1st active tab (should only be one at a time)
      this.closeTab(activeTabs[0]);
    }
  }
}
