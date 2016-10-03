(function() {
	'use strict';

	angular.module('yamaApp').config(complaintRoute);

	function complaintRoute($stateProvider) {
		$stateProvider.state('app.complaint', {
			url: '/complaint',
			templateUrl: 'app/complaint/complaint.html',
			controller: 'ComplaintCtrl as ctrl'
		});
	}
})();
