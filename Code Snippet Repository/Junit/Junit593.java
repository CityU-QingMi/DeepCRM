	private static <A extends Annotation> Optional<A> findMetaAnnotation(Class<A> annotationType,
			Annotation[] candidates, AnnotationCacheKey key, Set<Annotation> visited) {

		for (Annotation candidateAnnotation : candidates) {
			Class<? extends Annotation> candidateAnnotationType = candidateAnnotation.annotationType();
			if (!isInJavaLangAnnotationPackage(candidateAnnotationType) && visited.add(candidateAnnotation)) {
				Optional<A> metaAnnotation = findAnnotation(candidateAnnotationType, annotationType, visited);
				if (metaAnnotation.isPresent()) {
					annotationCache.put(key, metaAnnotation.get());
					return metaAnnotation;
				}
			}
		}
		return Optional.empty();
	}
