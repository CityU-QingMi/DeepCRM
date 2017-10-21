	private boolean annotationEquals(Object other) {
		if (this == other) {
			return true;
		}
		if (!annotationType().isInstance(other)) {
			return false;
		}

		for (Method attributeMethod : AnnotationUtils.getAttributeMethods(annotationType())) {
			Object thisValue = getAttributeValue(attributeMethod);
			Object otherValue = ReflectionUtils.invokeMethod(attributeMethod, other);
			if (!ObjectUtils.nullSafeEquals(thisValue, otherValue)) {
				return false;
			}
		}

		return true;
	}
