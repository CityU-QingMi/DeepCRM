	public static AnnotatedElement forAnnotations(final Annotation... annotations) {
		return new AnnotatedElement() {
			@Override
			@SuppressWarnings("unchecked")
			@Nullable
			public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
				for (Annotation ann : annotations) {
					if (ann.annotationType() == annotationClass) {
						return (T) ann;
					}
				}
				return null;
			}
			@Override
			public Annotation[] getAnnotations() {
				return annotations;
			}
			@Override
			public Annotation[] getDeclaredAnnotations() {
				return annotations;
			}
		};
	}
