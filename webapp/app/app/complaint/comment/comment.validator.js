(function() {
	'use strict';

	angular.module('yamaApp').run(commentValidator);

	function commentValidator($rootScope, $translate, validationSchema) {
		$rootScope.$on('$translateChangeSuccess', validate);

		function validate() {
			validationSchema.set('comment', {
				title: {
					'validations': 'required, minlength=4',
					'validate-on': 'blur',
					'messages': {
						'required': {
							'error': $translate.instant('complaint.comment.validation.name_required'),
							'success': 'Ok'
						},
						'minlength': {
							'error': $translate.instant('complaint.comment.validation.name_length'),
							'success': 'Ok'
						}
					}
				}
			});
		}
	}
})();
