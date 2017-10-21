	public static void processIdBagAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmIdBagCollectionType idBagAttributeJaxbMapping) {
		callback.addAttributeSource(
				new PluralAttributeSourceIdBagImpl(
						mappingDocument,
						idBagAttributeJaxbMapping,
						callback.getAttributeSourceContainer()
				)
		);
	}
