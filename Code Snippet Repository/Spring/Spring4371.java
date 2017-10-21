	@Nullable
	public static MultiValueMap<String, Object> getAllAnnotationAttributes(AnnotatedElement element,
			String annotationName, final boolean classValuesAsString, final boolean nestedAnnotationsAsMap) {

		final MultiValueMap<String, Object> attributesMap = new LinkedMultiValueMap<>();

		searchWithGetSemantics(element, null, annotationName, new SimpleAnnotationProcessor<Object>() {
			@Override
			@Nullable
			public Object process(@Nullable AnnotatedElement annotatedElement, Annotation annotation, int metaDepth) {
				AnnotationAttributes annotationAttributes = AnnotationUtils.getAnnotationAttributes(
						annotation, classValuesAsString, nestedAnnotationsAsMap);
				annotationAttributes.forEach(attributesMap::add);
				return CONTINUE;
			}
		});

		return (!attributesMap.isEmpty() ? attributesMap : null);
	}
