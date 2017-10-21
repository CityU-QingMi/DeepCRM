	@SuppressWarnings("")
	@Nullable
	public static <A extends Annotation> A getAnnotation(Annotation ann, Class<A> annotationType) {
		if (annotationType.isInstance(ann)) {
			return synthesizeAnnotation((A) ann);
		}
		Class<? extends Annotation> annotatedElement = ann.annotationType();
		try {
			return synthesizeAnnotation(annotatedElement.getAnnotation(annotationType), annotatedElement);
		}
		catch (Throwable ex) {
			handleIntrospectionFailure(annotatedElement, ex);
			return null;
		}
	}
