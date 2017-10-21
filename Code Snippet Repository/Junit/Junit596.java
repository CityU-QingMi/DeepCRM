	@SuppressWarnings("")
	private static <A extends Annotation> void findRepeatableAnnotations(Annotation[] candidates,
			Class<A> annotationType, Class<? extends Annotation> containerType, boolean inherited, Set<A> found,
			Set<Annotation> visited) {

		for (Annotation candidate : candidates) {
			Class<? extends Annotation> candidateAnnotationType = candidate.annotationType();
			if (!isInJavaLangAnnotationPackage(candidateAnnotationType) && visited.add(candidate)) {
				// Exact match?
				if (candidateAnnotationType.equals(annotationType)) {
					found.add(annotationType.cast(candidate));
				}
				// Container?
				else if (candidateAnnotationType.equals(containerType)) {
					// Note: it's not a legitimate containing annotation type if it doesn't declare
					// a 'value' attribute that returns an array of the contained annotation type.
					// See https://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.6.3
					Method method = ReflectionUtils.getMethod(containerType, "value").orElseThrow(
						() -> new JUnitException(String.format(
							"Container annotation type '%s' must declare a 'value' attribute of type %s[].",
							containerType, annotationType)));

					Annotation[] containedAnnotations = (Annotation[]) ReflectionUtils.invokeMethod(method, candidate);
					found.addAll((Collection<? extends A>) asList(containedAnnotations));
				}
				// Otherwise search recursively through the meta-annotation hierarchy...
				else {
					findRepeatableAnnotations(candidateAnnotationType, annotationType, containerType, inherited, found,
						visited);
				}
			}
		}
	}
