	@Override
	@Nullable
	public MultiValueMap<String, Object> getAllAnnotationAttributes(String annotationName, boolean classValuesAsString) {
		if (!this.attributesMap.containsKey(annotationName)) {
			return null;
		}
		MultiValueMap<String, Object> allAttributes = new LinkedMultiValueMap<>();
		List<AnnotationAttributes> attributesList = this.attributesMap.get(annotationName);
		if (attributesList != null) {
			for (AnnotationAttributes annotationAttributes : attributesList) {
				AnnotationAttributes convertedAttributes = AnnotationReadingVisitorUtils.convertClassValues(
						"method '" + getMethodName() + "'", this.classLoader, annotationAttributes, classValuesAsString);
				convertedAttributes.forEach(allAttributes::add);
			}
		}
		return allAttributes;
	}
