(function() {
	'use strict';

	angular.module('yamaApp').factory('RestCommentService', restCommentService);

	function restCommentService(Restangular) {
		return Restangular.service('comments');
	}
})();
