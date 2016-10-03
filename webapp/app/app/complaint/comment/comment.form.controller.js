(function() {
	'use strict';

	angular.module('yamaApp').controller('ComplaintCommentFormCtrl', commentFormController);

	function commentFormController($modalInstance, $validation, changeSecret, complaint, RestCommentService) {
		// jshint validthis: true
		var ctrl = this;

		ctrl.complaint = complaint;

		ctrl.comment = {
			complaint: complaint
		};
		ctrl.submit = submitComment;

		function error() {
			ctrl.error = true;
		}

		function submitComment(comment, form) {
			$validation.validate(form).success(submit);

			function submit() {
				ctrl.error = false;

				if (comment.id) {
					comment.put().then(success, error);
				} else {
					RestCommentService.post(comment).then(success, error);
				}
			}
		}

		function success(comment) {
			$modalInstance.close(comment);
		}
	}
})();
