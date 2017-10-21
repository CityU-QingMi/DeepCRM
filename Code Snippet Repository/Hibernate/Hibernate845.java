	public static void processDynamicComponentAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmDynamicComponentType dynamicComponentJaxbMapping,
			String logicalTableName,
			NaturalIdMutability naturalIdMutability) {
		callback.addAttributeSource(
				new SingularAttributeSourceEmbeddedImpl(
						mappingDocument,
						callback.getAttributeSourceContainer(),
						dynamicComponentJaxbMapping,
						naturalIdMutability,
						logicalTableName
				)
		);
	}
