	@Nullable
	private static <T> T searchWithFindSemantics(AnnotatedElement element,
			@Nullable Class<? extends Annotation> annotationType, @Nullable String annotationName,
			@Nullable Class<? extends Annotation> containerType, Processor<T> processor) {

		if (containerType != null && !processor.aggregates()) {
			throw new IllegalArgumentException(
				"Searches for repeatable annotations must supply an aggregating Processor");
		}

		try {
			return searchWithFindSemantics(
					element, annotationType, annotationName, containerType, processor, new HashSet<>(), 0);
		}
		catch (Throwable ex) {
			AnnotationUtils.rethrowAnnotationConfigurationException(ex);
			throw new IllegalStateException("Failed to introspect annotations on " + element, ex);
		}
	}
