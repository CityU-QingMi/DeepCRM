	public static void processBasicAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmBasicAttributeType basicAttributeJaxbMapping,
			String logicalTableName,
			NaturalIdMutability naturalIdMutability) {
		callback.addAttributeSource(
				new SingularAttributeSourceBasicImpl(
						mappingDocument,
						callback.getAttributeSourceContainer(),
						basicAttributeJaxbMapping,
						logicalTableName,
						naturalIdMutability
				)
		);
	}
