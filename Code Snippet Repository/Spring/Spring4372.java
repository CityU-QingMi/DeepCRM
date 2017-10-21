	public static boolean hasAnnotation(AnnotatedElement element, Class<? extends Annotation> annotationType) {
		Assert.notNull(element, "AnnotatedElement must not be null");
		Assert.notNull(annotationType, "'annotationType' must not be null");

		// Shortcut: directly present on the element, with no processing needed?
		if (element.isAnnotationPresent(annotationType)) {
			return true;
		}

		return Boolean.TRUE.equals(searchWithFindSemantics(element, annotationType, null, alwaysTrueAnnotationProcessor));
	}
