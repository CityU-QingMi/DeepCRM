	@SuppressWarnings({ "", "" })
	public static <T> T initialize(AnnotatedElement annotatedElement, T instance) {
		if (instance instanceof AnnotationConsumer) {
			Method method = findMethods(instance.getClass(), isAnnotationConsumerAcceptMethod, BOTTOM_UP).get(0);
			Class<? extends Annotation> annotationType = (Class<? extends Annotation>) method.getParameterTypes()[0];
			Annotation annotation = AnnotationUtils.findAnnotation(annotatedElement, annotationType) //
					.orElseThrow(() -> new JUnitException(instance.getClass().getName()
							+ " must be used with an annotation of type " + annotationType.getName()));
			initializeAnnotationConsumer((AnnotationConsumer) instance, annotation);
		}
		return instance;
	}
