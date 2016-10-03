(function() {
	'use strict';

	angular.module('yamaApp').config(applicationRoute);

	function applicationRoute($stateProvider) {
		$stateProvider.state('app.complaint.complaint', {
			url: '/complaints',
			templateUrl: 'app/complaint/complaint/complaint.list.html',
			controller: 'ComplaintComplaintListCtrl as ctrl'
		});
	}
})();
