	public static void processManyToOneAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmManyToOneType manyToOneAttributeJaxbMapping,
			String logicalTableName,
			NaturalIdMutability naturalIdMutability) {
		callback.addAttributeSource(
				new SingularAttributeSourceManyToOneImpl(
						mappingDocument,
						callback.getAttributeSourceContainer(),
						manyToOneAttributeJaxbMapping,
						logicalTableName,
						naturalIdMutability
				)
		);
	}
