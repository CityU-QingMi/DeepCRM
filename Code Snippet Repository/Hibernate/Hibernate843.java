	public static void processEmbeddedAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmCompositeAttributeType embeddedAttributeJaxbMapping,
			String logicalTableName,
			NaturalIdMutability naturalIdMutability) {
		callback.addAttributeSource(
				new SingularAttributeSourceEmbeddedImpl(
						mappingDocument,
						callback.getAttributeSourceContainer(),
						embeddedAttributeJaxbMapping,
						naturalIdMutability,
						logicalTableName
				)
		);
	}
