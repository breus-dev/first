(function() {
	'use strict';

	angular.module('yamaApp').controller('ComplaintCommentListCtrl', commentListController);

	function commentListController($location, $modal, angularPopupBoxes, RestCommentService) {
		// jshint validthis: true
		var ctrl = this;
		ctrl.openForm = openCommentForm;
		ctrl.page = 1;
		ctrl.remove = removeComment;
		ctrl.searchParams = $location.search();
		ctrl.searchParams.hash = 0;
		ctrl.search = searchComments;
		ctrl.search();

		function onCommentsLoaded(comments) {
			ctrl.comments = comments;
			ctrl.page = comments.meta.number + 1;
		}

		function openCommentForm(comment, changeSecret) {
			var modal = $modal.open({
				templateUrl: 'app/complaint/comment/comment.form.html',
				controller: 'ComplaintCommentFormCtrl as ctrl',
				size: 'md',
				resolve: {
					comment: function() { return comment; },
					changeSecret: function() { return changeSecret; }
				}
			});

			modal.result.then(success);

			function success(result) {
				ctrl.searchParams.q = result.name;
				ctrl.search();
			}
		}

		

		function removeComment(comment) {
			angularPopupBoxes.confirm('Are you sure want to delete this data?')
					.result.then(remove);

			function remove() {
				comment.remove().then(ctrl.search);
			}
		}

		function searchComments() {
			ctrl.searchParams.hash++;
			ctrl.searchParams.page = ctrl.page - 1;

			$location.search(ctrl.searchParams);

			RestCommentService.getList(ctrl.searchParams).then(onCommentsLoaded);
		}
	}
})();
