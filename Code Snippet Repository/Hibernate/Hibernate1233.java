	private static AttributeConverter instantiateAttributeConverter(Class<? extends AttributeConverter> attributeConverterClass) {
		try {
			return attributeConverterClass.newInstance();
		}
		catch (Exception e) {
			throw new AnnotationException(
					"Unable to instantiate AttributeConverter [" + attributeConverterClass.getName() + "]",
					e
			);
		}
	}
