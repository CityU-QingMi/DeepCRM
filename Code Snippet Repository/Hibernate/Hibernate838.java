	public static void processSetAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmSetType setAttributeJaxbMapping) {
		callback.addAttributeSource(
				new PluralAttributeSourceSetImpl(
						mappingDocument,
						setAttributeJaxbMapping,
						callback.getAttributeSourceContainer()
				)
		);
	}
