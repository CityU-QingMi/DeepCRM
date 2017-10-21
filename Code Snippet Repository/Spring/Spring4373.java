	@Nullable
	public static <A extends Annotation> A findMergedAnnotation(AnnotatedElement element, Class<A> annotationType) {
		Assert.notNull(annotationType, "'annotationType' must not be null");

		// Shortcut: directly present on the element, with no merging needed?
		if (!(element instanceof Class)) {
			// Do not use this shortcut against a Class: Inherited annotations
			// would get preferred over locally declared composed annotations.
			A annotation = element.getAnnotation(annotationType);
			if (annotation != null) {
				return AnnotationUtils.synthesizeAnnotation(annotation, element);
			}
		}

		// Exhaustive retrieval of merged annotation attributes...
		AnnotationAttributes attributes = findMergedAnnotationAttributes(element, annotationType, false, false);
		return (attributes != null ? AnnotationUtils.synthesizeAnnotation(attributes, annotationType, element) : null);
	}
