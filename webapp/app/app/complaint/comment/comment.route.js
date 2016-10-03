(function() {
	'use strict';

	angular.module('yamaApp').config(applicationRoute);

	function applicationRoute($stateProvider) {
		$stateProvider.state('app.complaint.comment', {
			url: '/comments',
			templateUrl: 'app/complaint/comment/comment.list.html',
			controller: 'ComplaintCommentListCtrl as ctrl'
		});
	}
})();
