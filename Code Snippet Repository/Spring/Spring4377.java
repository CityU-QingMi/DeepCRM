	@Nullable
	private static <T> T searchWithGetSemantics(AnnotatedElement element,
			@Nullable Class<? extends Annotation> annotationType, @Nullable String annotationName,
			@Nullable Class<? extends Annotation> containerType, Processor<T> processor,
			Set<AnnotatedElement> visited, int metaDepth) {

		Assert.notNull(element, "AnnotatedElement must not be null");

		if (visited.add(element)) {
			try {
				// Start searching within locally declared annotations
				List<Annotation> declaredAnnotations = Arrays.asList(element.getDeclaredAnnotations());
				T result = searchWithGetSemanticsInAnnotations(element, declaredAnnotations,
						annotationType, annotationName, containerType, processor, visited, metaDepth);
				if (result != null) {
					return result;
				}

				if (element instanceof Class) { // otherwise getAnnotations doesn't return anything new
					List<Annotation> inheritedAnnotations = new ArrayList<>();
					for (Annotation annotation : element.getAnnotations()) {
						if (!declaredAnnotations.contains(annotation)) {
							inheritedAnnotations.add(annotation);
						}
					}

					// Continue searching within inherited annotations
					result = searchWithGetSemanticsInAnnotations(element, inheritedAnnotations,
							annotationType, annotationName, containerType, processor, visited, metaDepth);
					if (result != null) {
						return result;
					}
				}
			}
			catch (Throwable ex) {
				AnnotationUtils.handleIntrospectionFailure(element, ex);
			}
		}

		return null;
	}
