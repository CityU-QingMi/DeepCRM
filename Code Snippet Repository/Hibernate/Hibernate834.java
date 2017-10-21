	public static void processMapAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmMapType mapAttributesJaxbMapping) {
		callback.addAttributeSource(
				new PluralAttributeSourceMapImpl(
						mappingDocument,
						mapAttributesJaxbMapping,
						callback.getAttributeSourceContainer()
				)
		);
	}
