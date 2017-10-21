	@SuppressWarnings("")
	static Set<AnnotationAttributes> attributesForRepeatable(AnnotationMetadata metadata,
			String containerClassName, String annotationClassName) {

		Set<AnnotationAttributes> result = new LinkedHashSet<>();
		addAttributesIfNotNull(result, metadata.getAnnotationAttributes(annotationClassName, false));

		Map<String, Object> container = metadata.getAnnotationAttributes(containerClassName, false);
		if (container != null && container.containsKey("value")) {
			for (Map<String, Object> containedAttributes : (Map<String, Object>[]) container.get("value")) {
				addAttributesIfNotNull(result, containedAttributes);
			}
		}
		return Collections.unmodifiableSet(result);
	}
