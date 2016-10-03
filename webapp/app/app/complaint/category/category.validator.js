(function() {
	'use strict';

	angular.module('yamaApp').run(categoryValidator);

	function categoryValidator($rootScope, $translate, validationSchema) {
		$rootScope.$on('$translateChangeSuccess', validate);

		function validate() {
			validationSchema.set('category', {
				name: {
					'validations': 'required, minlength=4',
					'validate-on': 'blur',
					'messages': {
						'required': {
							'error': $translate.instant('complaint.category.validation.name_required'),
							'success': 'Ok'
						},
						'minlength': {
							'error': $translate.instant('complaint.category.validation.name_length'),
							'success': 'Ok'
						}
					}
				}
			});
		}
	}
})();
