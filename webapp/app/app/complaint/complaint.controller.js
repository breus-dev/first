(function() {
	'use strict';

	angular.module('yamaApp').controller('ComplaintCtrl', complaintController);

	function complaintController($state) {
		// jshint validthis: true
		var ctrl = this;
		ctrl.menus = [
		              { menu: 'Complaints', icon: 'book', ref: 'app.complaint.complaint' },
		              { menu: 'Categories', icon: 'users', ref: 'app.complaint.category' }
		];
		ctrl.state = $state;

		if (ctrl.state.current.name === 'app.complaint') {
			ctrl.state.go(ctrl.menus[0].ref);
		}

	}
})();
