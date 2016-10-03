(function() {
	'use strict';

	angular.module('yamaApp').run(complaintValidator);

	function complaintValidator($rootScope, $translate, validationSchema) {
		$rootScope.$on('$translateChangeSuccess', validate);

		function validate() {
			validationSchema.set('complaint', {
				name: {
					'validations': 'required, minlength=4',
					'validate-on': 'blur',
					'messages': {
						'required': {
							'error': $translate.instant('complaint.complaint.validation.name_required'),
							'success': 'Ok'
						},
						'minlength': {
							'error': $translate.instant('complaint.complaint.validation.name_length'),
							'success': 'Ok'
						}
					}
				}
			});
		}
	}
})();
