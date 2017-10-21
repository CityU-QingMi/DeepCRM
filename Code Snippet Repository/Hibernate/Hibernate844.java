	private static void processNestedEmbeddedElement(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmNestedCompositeElementType attributeJaxbMapping,
			String logicalTableName,
			NaturalIdMutability naturalIdMutability) {
		callback.addAttributeSource(
				new SingularAttributeSourceEmbeddedImpl(
						mappingDocument,
						callback.getAttributeSourceContainer(),
						attributeJaxbMapping,
						naturalIdMutability,
						logicalTableName
				)
		);
	}
