	private int annotationHashCode() {
		int result = 0;

		for (Method attributeMethod : AnnotationUtils.getAttributeMethods(annotationType())) {
			Object value = getAttributeValue(attributeMethod);
			int hashCode;
			if (value.getClass().isArray()) {
				hashCode = hashCodeForArray(value);
			}
			else {
				hashCode = value.hashCode();
			}
			result += (127 * attributeMethod.getName().hashCode()) ^ hashCode;
		}

		return result;
	}
