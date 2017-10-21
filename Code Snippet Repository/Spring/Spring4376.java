	@Nullable
	private static <T> T searchWithGetSemantics(AnnotatedElement element,
			@Nullable Class<? extends Annotation> annotationType, @Nullable String annotationName,
			@Nullable Class<? extends Annotation> containerType, Processor<T> processor) {

		try {
			return searchWithGetSemantics(element, annotationType, annotationName, containerType, processor,
					new HashSet<>(), 0);
		}
		catch (Throwable ex) {
			AnnotationUtils.rethrowAnnotationConfigurationException(ex);
			throw new IllegalStateException("Failed to introspect annotations on " + element, ex);
		}
	}
