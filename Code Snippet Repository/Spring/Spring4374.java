	public static <A extends Annotation> Set<A> findAllMergedAnnotations(AnnotatedElement element,
			Class<A> annotationType) {

		Assert.notNull(element, "AnnotatedElement must not be null");
		Assert.notNull(annotationType, "'annotationType' must not be null");

		MergedAnnotationAttributesProcessor processor = new MergedAnnotationAttributesProcessor(false, false, true);
		searchWithFindSemantics(element, annotationType, null, processor);
		return postProcessAndSynthesizeAggregatedResults(element, annotationType, processor.getAggregatedResults());
	}
