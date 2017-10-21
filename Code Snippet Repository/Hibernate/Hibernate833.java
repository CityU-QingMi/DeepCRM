	public static void processAttributes(
			MappingDocument mappingDocument,
			Callback callback,
			List attributeMappings,
			String logicalTableName,
			NaturalIdMutability naturalIdMutability) {
		for ( Object rawAttributeMapping : attributeMappings ) {
			processAttribute(
					mappingDocument,
					callback,
					rawAttributeMapping,
					logicalTableName,
					naturalIdMutability
			);
		}
	}
