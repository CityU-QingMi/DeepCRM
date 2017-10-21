	public static void processPrimitiveArrayAttribute(
			MappingDocument mappingDocument,
			Callback callback,
			JaxbHbmPrimitiveArrayType primitiveArrayAttributeJaxbMapping) {
		callback.addAttributeSource(
				new PluralAttributeSourcePrimitiveArrayImpl(
						mappingDocument,
						primitiveArrayAttributeJaxbMapping,
						callback.getAttributeSourceContainer()
				)
		);
	}
