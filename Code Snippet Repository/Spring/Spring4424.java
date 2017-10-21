		@SuppressWarnings("")
		private List<A> getValue(AnnotatedElement element, Annotation annotation) {
			try {
				List<A> synthesizedAnnotations = new ArrayList<>();
				A[] value = (A[]) AnnotationUtils.getValue(annotation);
				if (value != null) {
					for (A anno : value) {
						synthesizedAnnotations.add(synthesizeAnnotation(anno, element));
					}
				}
				return synthesizedAnnotations;
			}
			catch (Throwable ex) {
				handleIntrospectionFailure(element, ex);
			}
			// Unable to read value from repeating annotation container -> ignore it.
			return Collections.emptyList();
		}
