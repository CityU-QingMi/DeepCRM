	private static <A extends Annotation> Set<A> getRepeatableAnnotations(AnnotatedElement annotatedElement,
			Class<A> annotationType, @Nullable Class<? extends Annotation> containerAnnotationType, boolean declaredMode) {

		Assert.notNull(annotatedElement, "AnnotatedElement must not be null");
		Assert.notNull(annotationType, "Annotation type must not be null");

		try {
			if (annotatedElement instanceof Method) {
				annotatedElement = BridgeMethodResolver.findBridgedMethod((Method) annotatedElement);
			}
			return new AnnotationCollector<>(annotationType, containerAnnotationType, declaredMode).getResult(annotatedElement);
		}
		catch (Throwable ex) {
			handleIntrospectionFailure(annotatedElement, ex);
			return Collections.emptySet();
		}
	}
