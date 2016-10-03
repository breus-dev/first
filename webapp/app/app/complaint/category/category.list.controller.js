(function() {
	'use strict';

	angular.module('yamaApp').controller('ComplaintCategoryListCtrl', categoryListController);

	function categoryListController($location, $modal, angularPopupBoxes, RestCategoryService) {
		// jshint validthis: true
		var ctrl = this;
		ctrl.openForm = openCategoryForm;
		ctrl.page = 1;
		ctrl.remove = removeCategory;
		ctrl.searchParams = $location.search();
		ctrl.searchParams.hash = 0;
		ctrl.search = searchCategories;
		ctrl.search();

		function onCategoriesLoaded(categories) {
			ctrl.categories = categories;
			ctrl.page = categories.meta.number + 1;
		}

		function openCategoryForm(category, changeSecret) {
			var modal = $modal.open({
				templateUrl: 'app/complaint/category/category.form.html',
				controller: 'ComplaintCategoryFormCtrl as ctrl',
				size: 'md',
				resolve: {
					category: function() { return category; },
					changeSecret: function() { return changeSecret; }
				}
			});

			modal.result.then(success);

			function success(result) {
				ctrl.searchParams.q = result.name;
				ctrl.search();
			}
		}

		function removeCategory(category) {
			angularPopupBoxes.confirm('Are you sure want to delete this data?')
					.result.then(remove);

			function remove() {
				category.remove().then(ctrl.search);
			}
		}

		function searchCategories() {
			ctrl.searchParams.hash++;
			ctrl.searchParams.page = ctrl.page - 1;

			$location.search(ctrl.searchParams);

			RestCategoryService.getList(ctrl.searchParams).then(onCategoriesLoaded);
		}
	}
})();
