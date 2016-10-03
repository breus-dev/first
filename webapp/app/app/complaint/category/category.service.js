(function() {
	'use strict';

	angular.module('yamaApp').factory('RestCategoryService', restCategoryService);

	function restCategoryService(Restangular) {
		return Restangular.service('categories');
	}
})();
