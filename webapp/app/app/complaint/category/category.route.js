(function() {
	'use strict';

	angular.module('yamaApp').config(applicationRoute);

	function applicationRoute($stateProvider) {
		$stateProvider.state('app.complaint.category', {
			url: '/categories',
			templateUrl: 'app/complaint/category/category.list.html',
			controller: 'ComplaintCategoryListCtrl as ctrl'
		});
	}
})();
