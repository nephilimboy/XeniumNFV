/* after changing this file run 'yarn run webpack:build:vendor' or 'yarn install' or 'yarn run webpack:build' */
/* tslint:disable */
// import '../content/css/vendor.css';


// Smartadmin Dependencies
window['jQuery'] = require('jquery');
window['$'] = window['jQuery'];
// import 'jquery-ui-npm/jquery-ui.min.js'

import '../content/css/your_style.css';
import '../content/css/bootstrap.min.css';
import '../content/css/font-awesome.min.css';
import '../content/css/smartadmin-production-plugins.min.css';
import '../content/css/smartadmin-production.min.css';
import '../content/css/smartadmin-skins.min.css';
import '../content/css/smartadmin-angular-next.css';
import '../content/css/demo.min.css';

require('bootstrap/js/tooltip.js'); // required for X-editable
require('bootstrap/js/popover.js'); // required for X-editable
require('bootstrap/js/dropdown.js'); // required for bootstrap-colorpicker
require('bootstrap/js/tab.js'); //
require('bootstrap/js/modal.js'); //

window['moment'] = require('moment');

// import 'imports-loader?jQuery=jquery!jquery-color/jquery.color.js'

require('smartadmin-plugins/notification/SmartNotification.min.js');

// require('script-loader!../content/jquery.easypiechart.js');
// require('jsplumb/dist/js/jsplumb.min.js');

