		@SafeVarargs
		public final Builder<T> annotNotPresent(Class<? extends Annotation>... annotationTypes) {
			String message = "annotationNotPresent=" + Arrays.toString(annotationTypes);
			addFilter(message, method -> {
				if (annotationTypes.length != 0) {
					return Arrays.stream(annotationTypes).noneMatch(annotType ->
							AnnotatedElementUtils.findMergedAnnotation(method, annotType) != null);
				}
				else {
					return method.getAnnotations().length == 0;
				}
			});
			return this;
		}
