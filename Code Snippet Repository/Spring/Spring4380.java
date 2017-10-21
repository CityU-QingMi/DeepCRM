	@SuppressWarnings("")
	private static <A extends Annotation> A[] getRawAnnotationsFromContainer(
			@Nullable AnnotatedElement element, Annotation container) {

		try {
			A[] value = (A[]) AnnotationUtils.getValue(container);
			if (value != null) {
				return value;
			}
		}
		catch (Throwable ex) {
			AnnotationUtils.handleIntrospectionFailure(element, ex);
		}
		// Unable to read value from repeating annotation container -> ignore it.
		return (A[]) EMPTY_ANNOTATION_ARRAY;
	}
