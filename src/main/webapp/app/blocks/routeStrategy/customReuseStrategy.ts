// import { ActivatedRouteSnapshot, RouteReuseStrategy, DetachedRouteHandle } from '@angular/router';

// interface RouteStorageObject {
//     snapshot: ActivatedRouteSnapshot;
//     handle: DetachedRouteHandle;
// }

// export class CustomReuseStrategy implements RouteReuseStrategy {

//     storedRoutes: { [key: string]: DetachedRouteHandle } = {};

//     shouldDetach(route: ActivatedRouteSnapshot): boolean {
//         return true;
//     }

//     store(route: ActivatedRouteSnapshot, handle: DetachedRouteHandle): void {
//         console.log('+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++');
//         console.log(route.url.join("/") || route.parent.url.join('/'));
//         console.log(this.storedRoutes);
//       this.storedRoutes[route.url.join("/") || route.parent.url.join('/')] = handle;
//     }

//     shouldAttach(route: ActivatedRouteSnapshot): boolean {
//         return !!this.storedRoutes[route.url.join("/") || route.parent.url.join('/')];

//     }

//     retrieve(route: ActivatedRouteSnapshot): DetachedRouteHandle {
//         return this.storedRoutes[route.url.join("/") || route.parent.url.join('/')];
//     }

//     shouldReuseRoute(future: ActivatedRouteSnapshot, curr: ActivatedRouteSnapshot): boolean {
//         return future.routeConfig === curr.routeConfig;
//     }

//     private compareObjects(base: any, compare: any): boolean {

//         // loop through all properties in base object
//         for (let baseProperty in base) {

//             // determine if comparrison object has that property, if not: return false
//             if (compare.hasOwnProperty(baseProperty)) {
//                 switch(typeof base[baseProperty]) {
//                     // if one is object and other is not: return false
//                     // if they are both objects, recursively call this comparison function
//                     case 'object':
//                         if ( typeof compare[baseProperty] !== 'object' || !this.compareObjects(base[baseProperty], compare[baseProperty]) ) { return false; } break;
//                     // if one is function and other is not: return false
//                     // if both are functions, compare function.toString() results
//                     case 'function':
//                         if ( typeof compare[baseProperty] !== 'function' || base[baseProperty].toString() !== compare[baseProperty].toString() ) { return false; } break;
//                     // otherwise, see if they are equal using coercive comparison
//                     default:
//                         if ( base[baseProperty] != compare[baseProperty] ) { return false; }
//                 }
//             } else {
//                 return false;
//             }
//         }

//         // returns true only after false HAS NOT BEEN returned through all loops
//         return true;
//     }
// }










































































// // import { ActivatedRouteSnapshot, RouteReuseStrategy, DetachedRouteHandle } from '@angular/router';

// // interface RouteStorageObject {
// //     snapshot: ActivatedRouteSnapshot;
// //     handle: DetachedRouteHandle;
// // }

// // export class CustomReuseStrategy implements RouteReuseStrategy {

// //     storedRoutes: { [key: string]: RouteStorageObject } = {};

// //     shouldDetach(route: ActivatedRouteSnapshot): boolean {
// //         let detach: boolean = true;
// //         console.log("detaching", route, "return: ", detach);
// //         return detach;
// //     }

// //     store(route: ActivatedRouteSnapshot, handle: DetachedRouteHandle): void {
// //         let storedRoute: RouteStorageObject = {
// //             snapshot: route,
// //             handle: handle
// //         };

// //         console.log( "store:", storedRoute, "into: ", this.storedRoutes );
// //         // routes are stored by path - the key is the path name, and the handle is stored under it so that you can only ever have one object stored for a single path
// //         this.storedRoutes[route.routeConfig.path] = storedRoute;
// //     }

// //     shouldAttach(route: ActivatedRouteSnapshot): boolean {

// //         // this will be true if the route has been stored before
// //         let canAttach: boolean = !!route.routeConfig && !!this.storedRoutes[route.routeConfig.path];

// //         // this decides whether the route already stored should be rendered in place of the requested route, and is the return value
// //         // at this point we already know that the paths match because the storedResults key is the route.routeConfig.path
// //         // so, if the route.params and route.queryParams also match, then we should reuse the component
// //         if (canAttach) {
// //             let willAttach: boolean = true;
// //             console.log("param comparison:");
// //             console.log(this.compareObjects(route.params, this.storedRoutes[route.routeConfig.path].snapshot.params));
// //             console.log("query param comparison");
// //             console.log(this.compareObjects(route.queryParams, this.storedRoutes[route.routeConfig.path].snapshot.queryParams));

// //             let paramsMatch: boolean = this.compareObjects(route.params, this.storedRoutes[route.routeConfig.path].snapshot.params);
// //             let queryParamsMatch: boolean = this.compareObjects(route.queryParams, this.storedRoutes[route.routeConfig.path].snapshot.queryParams);

// //             console.log("deciding to attach...", route, "does it match?", this.storedRoutes[route.routeConfig.path].snapshot, "return: ", paramsMatch && queryParamsMatch);
// //             return paramsMatch && queryParamsMatch;
// //         } else {
// //             return false;
// //         }
// //     }


// //     retrieve(route: ActivatedRouteSnapshot): DetachedRouteHandle {

// //         // return null if the path does not have a routerConfig OR if there is no stored route for that routerConfig
// //         if (!route.routeConfig || !this.storedRoutes[route.routeConfig.path]) return null;
// //         console.log("retrieving", "return: ", this.storedRoutes[route.routeConfig.path]);

// //         /** returns handle when the route.routeConfig.path is already stored */
// //         return this.storedRoutes[route.routeConfig.path].handle;
// //     }

// //     shouldReuseRoute(future: ActivatedRouteSnapshot, curr: ActivatedRouteSnapshot): boolean {
// //         console.log("deciding to reuse", "future", future.routeConfig, "current", curr.routeConfig, "return: ", future.routeConfig === curr.routeConfig);
// //         return future.routeConfig === curr.routeConfig;
// //     }

// //     private compareObjects(base: any, compare: any): boolean {

// //         // loop through all properties in base object
// //         for (let baseProperty in base) {

// //             // determine if comparrison object has that property, if not: return false
// //             if (compare.hasOwnProperty(baseProperty)) {
// //                 switch(typeof base[baseProperty]) {
// //                     // if one is object and other is not: return false
// //                     // if they are both objects, recursively call this comparison function
// //                     case 'object':
// //                         if ( typeof compare[baseProperty] !== 'object' || !this.compareObjects(base[baseProperty], compare[baseProperty]) ) { return false; } break;
// //                     // if one is function and other is not: return false
// //                     // if both are functions, compare function.toString() results
// //                     case 'function':
// //                         if ( typeof compare[baseProperty] !== 'function' || base[baseProperty].toString() !== compare[baseProperty].toString() ) { return false; } break;
// //                     // otherwise, see if they are equal using coercive comparison
// //                     default:
// //                         if ( base[baseProperty] != compare[baseProperty] ) { return false; }
// //                 }
// //             } else {
// //                 return false;
// //             }
// //         }

// //         // returns true only after false HAS NOT BEEN returned through all loops
// //         return true;
// //     }
// // }