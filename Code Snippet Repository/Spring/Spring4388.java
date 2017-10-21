	@Nullable
	public static AnnotationAttributes getMergedAnnotationAttributes(AnnotatedElement element,
			String annotationName, boolean classValuesAsString, boolean nestedAnnotationsAsMap) {

		Assert.hasLength(annotationName, "'annotationName' must not be null or empty");
		AnnotationAttributes attributes = searchWithGetSemantics(element, null, annotationName,
				new MergedAnnotationAttributesProcessor(classValuesAsString, nestedAnnotationsAsMap));
		AnnotationUtils.postProcessAnnotationAttributes(element, attributes, classValuesAsString, nestedAnnotationsAsMap);
		return attributes;
	}
