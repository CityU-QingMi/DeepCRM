	public static void processArrayAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmArrayType arrayAttributeJaxbMapping) {
		callback.addAttributeSource(
				new PluralAttributeSourceArrayImpl(
						mappingDocument,
						arrayAttributeJaxbMapping,
						callback.getAttributeSourceContainer()
				)
		);
	}
