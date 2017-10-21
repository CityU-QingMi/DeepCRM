	public static void processListAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmListType listAttributeJaxbMapping) {
		callback.addAttributeSource(
				new PluralAttributeSourceListImpl(
						mappingDocument,
						listAttributeJaxbMapping,
						callback.getAttributeSourceContainer()
				)
		);
	}
