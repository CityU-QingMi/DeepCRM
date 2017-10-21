	private static boolean hasMetaAnnotationTypes(AnnotatedElement element, @Nullable Class<? extends Annotation> annotationType,
			@Nullable String annotationName) {

		return Boolean.TRUE.equals(
			searchWithGetSemantics(element, annotationType, annotationName, new SimpleAnnotationProcessor<Boolean>() {

				@Override
				@Nullable
				public Boolean process(@Nullable AnnotatedElement annotatedElement, Annotation annotation, int metaDepth) {
					return (metaDepth > 0 ? Boolean.TRUE : CONTINUE);
				}
			}));
	}
