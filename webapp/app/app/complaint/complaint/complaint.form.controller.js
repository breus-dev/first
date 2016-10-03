(function() {
	'use strict';

	angular.module('yamaApp').controller('ComplaintComplaintFormCtrl', complaintFormController);

	function complaintFormController($modalInstance, $validation, changeSecret, complaint, RestComplaintService, RestCategoryService) {
		// jshint validthis: true
		var ctrl = this;
		
		ctrl.complaint = complaint || {};
		ctrl.loadCategories = loadCategories;
		ctrl.categories = [];
		ctrl.submit = submitComplaint;

		function error() {
			ctrl.error = true;
		}
		function loadCategories(search){
			RestCategoryService.getList({ q: search}).then(oncategoryloaded);
			function oncategoryloaded(categories){
				ctrl.categories = categories;
			}
		}
		function submitComplaint(complaint, form) {
			$validation.validate(form).success(submit);

			function submit() {
				ctrl.error = false;

				if (complaint.id) {
					complaint.put().then(success, error);
				} else {
					RestComplaintService.post(complaint).then(success, error);
				}
			}
		}

		function success(complaint) {
			$modalInstance.close(complaint);
		}
	}
})();
