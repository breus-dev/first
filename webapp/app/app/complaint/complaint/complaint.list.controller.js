(function() {
	'use strict';

	angular.module('yamaApp').controller('ComplaintComplaintListCtrl', complaintListController);

	function complaintListController($location, $modal, angularPopupBoxes, RestComplaintService, RestCommentService) {
		// jshint validthis: true
		var ctrl = this;
		ctrl.openForm = openComplaintForm;
		ctrl.openFormComment = openCommentForm;
		ctrl.updateStatus = updateStatus;
		ctrl.page = 1;
		ctrl.remove = removeComplaint;
		ctrl.searchParams = $location.search();
		ctrl.searchParams.hash = 0;
		ctrl.search = searchComplaints;
		ctrl.search();
		ctrl.submitComment = submitComment;

		function onComplaintsLoaded(complaints) {
			ctrl.complaints = complaints;
			ctrl.page = complaints.meta.number + 1;

			for (var i = 0; i < complaints.length; i++) {
				var complaint = complaints[i];

				addCommentsToComplaint(complaint);
			}

			function addCommentsToComplaint(complaint) {
				complaint.one('comments').getList().then(loadComments);

				function loadComments(comments) {
					complaint.comments = comments;
				}
			}
			function submit() {
				ctrl.error = false;

				if (complaint.id) {
					complaint.put().then(success, error);
				} else {
					RestCommentService.post(comment).then(success, error);
				}
			}
		}


		
		function openComplaintForm(complaint, changeSecret) {
			var modal = $modal.open({
				templateUrl: 'app/complaint/complaint/complaint.form.html',
				controller: 'ComplaintComplaintFormCtrl as ctrl',
				size: 'md',
				resolve: {
					complaint: function() { return complaint; },
					changeSecret: function() { return changeSecret; }
				}
			});

			modal.result.then(success);

			function success(result) {
				ctrl.searchParams.q = result.name;
				ctrl.search();
			}
		}

		function errorStatus(){
			ctrl.error = true;
		}
		function succesStatus(){
		}
		
		function submitComment(complaint) {
			var comment = {};
			comment.comment = complaint.comment;
			comment.complaint = complaint;

			RestCommentService.post(complaint).success(onSuccess);

			function onSuccess() {
				complaint.comment = '';
			}
		}


		function openCommentForm(complaint, changeSecret) {
			var modal = $modal.open({
				templateUrl: 'app/complaint/comment/comment.form.html',
				controller: 'ComplaintCommentFormCtrl as ctrl',
				size: 'md',
				resolve: {
					complaint: function() { return complaint; },
					changeSecret: function() { return changeSecret; }
				}
			});

			modal.result.then(success);

			function success(result) {
				ctrl.searchParams.q = result.name;
				ctrl.search();
			}
		}
		
		function errorStatus(){
			ctrl.error = true;
		}
		function succesStatus(){
		}
		
		function updateStatus(complaint, status) {
			complaint.status = status || complaint.status;
			complaint.put().then(succesStatus, errorStatus);

		}


		function removeComplaint(complaint) {
			angularPopupBoxes.confirm('Are you sure want to delete this data?')
					.result.then(remove);

			function remove() {
				complaint.remove().then(ctrl.search);
			}
		}

		function searchComplaints() {
			ctrl.searchParams.hash++;
			ctrl.searchParams.page = ctrl.page - 1;

			$location.search(ctrl.searchParams);

			RestComplaintService.getList(ctrl.searchParams).then(onComplaintsLoaded);
		}
	}
})();
