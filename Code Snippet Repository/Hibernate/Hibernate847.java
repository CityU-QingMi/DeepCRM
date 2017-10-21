	public static void processOneToOneAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmOneToOneType oneToOneAttributeJaxbMapping,
			String logicalTableName,
			NaturalIdMutability naturalIdMutability) {
		callback.addAttributeSource(
				new SingularAttributeSourceOneToOneImpl(
						mappingDocument,
						callback.getAttributeSourceContainer(),
						oneToOneAttributeJaxbMapping,
						logicalTableName,
						naturalIdMutability
				)
		);
	}
