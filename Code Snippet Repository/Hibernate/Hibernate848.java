	public static void processAnyAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmAnyAssociationType anyAttributeJaxbMapping,
			String logicalTableName,
			NaturalIdMutability naturalIdMutability) {
		callback.addAttributeSource(
				new SingularAttributeSourceAnyImpl(
						mappingDocument,
						callback.getAttributeSourceContainer(),
						anyAttributeJaxbMapping,
						logicalTableName,
						naturalIdMutability
				)
		);
	}
