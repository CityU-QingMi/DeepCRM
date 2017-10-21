	public static <T extends Annotation> T locateAnnotation(
			Class<T> annotationClass,
			FrameworkMethod frameworkMethod,
			TestClass testClass) {
		T annotation = frameworkMethod.getAnnotation( annotationClass );
		if ( annotation == null ) {
			annotation = testClass.getJavaClass().getAnnotation( annotationClass );
		}
		return annotation;
	}
