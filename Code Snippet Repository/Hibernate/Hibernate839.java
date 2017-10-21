	public static void processBagAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmBagCollectionType bagAttributeJaxbMapping) {
		callback.addAttributeSource(
				new PluralAttributeSourceBagImpl(
						mappingDocument,
						bagAttributeJaxbMapping,
						callback.getAttributeSourceContainer()
				)
		);
	}
