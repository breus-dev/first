(function() {
	'use strict';

	angular.module('yamaApp').controller('ComplaintCategoryFormCtrl', categoryFormController);

	function categoryFormController($modalInstance, $validation, changeSecret, category, RestCategoryService) {
		// jshint validthis: true
		var ctrl = this;
		ctrl.category = category;
		ctrl.submit = submitCategory;

		function error() {
			ctrl.error = true;
		}

		function submitCategory(category, form) {
			$validation.validate(form).success(submit);

			function submit() {
				ctrl.error = false;

				if (category.id) {
					category.put().then(success, error);
				} else {
					RestCategoryService.post(category).then(success, error);
				}
			}
		}

		function success(category) {
			$modalInstance.close(category);
		}
	}
})();
