(function() {
	'use strict';

	angular.module('yamaApp').factory('RestComplaintService', restComplaintService);

	function restComplaintService(Restangular) {
		return Restangular.service('complaints');
	}
})();
