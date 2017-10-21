	private Object getAttributeValue(Method attributeMethod) {
		String attributeName = attributeMethod.getName();
		Object value = this.valueCache.get(attributeName);
		if (value == null) {
			value = this.attributeExtractor.getAttributeValue(attributeMethod);
			if (value == null) {
				String msg = String.format("%s returned null for attribute name [%s] from attribute source [%s]",
						this.attributeExtractor.getClass().getName(), attributeName, this.attributeExtractor.getSource());
				throw new IllegalStateException(msg);
			}

			// Synthesize nested annotations before returning them.
			if (value instanceof Annotation) {
				value = AnnotationUtils.synthesizeAnnotation((Annotation) value, this.attributeExtractor.getAnnotatedElement());
			}
			else if (value instanceof Annotation[]) {
				value = AnnotationUtils.synthesizeAnnotationArray((Annotation[]) value, this.attributeExtractor.getAnnotatedElement());
			}

			this.valueCache.put(attributeName, value);
		}

		// Clone arrays so that users cannot alter the contents of values in our cache.
		if (value.getClass().isArray()) {
			value = cloneArray(value);
		}

		return value;
	}
