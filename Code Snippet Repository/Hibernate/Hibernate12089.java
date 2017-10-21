	private void processClassHierarchy(Class testClass) {
		// NOTE recursive on itself
		for ( Method method : testClass.getDeclaredMethods() ) {
			if ( method.getAnnotation( CallbackType.BEFORE_CLASS_ONCE.annotationClass ) != null ) {
				addBeforeClassOnceCallback( method );
			}
			if ( method.getAnnotation( CallbackType.AFTER_CLASS_ONCE.annotationClass ) != null ) {
				addAfterClassOnceCallback( method );
			}
			if ( method.getAnnotation( CallbackType.ON_FAILURE.annotationClass ) != null ) {
				addOnFailureCallback( method );
			}
			if ( method.getAnnotation( CallbackType.ON_EXPECTED_FAILURE.annotationClass ) != null ) {
				addOnExpectedFailureCallback( method );
			}
		}

		Class superClass = testClass.getSuperclass();
		if ( superClass != null ) {
			processClassHierarchy( superClass );
		}
	}
