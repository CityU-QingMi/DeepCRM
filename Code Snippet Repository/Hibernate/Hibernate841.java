	public static void processCompositeKeySubAttributes(
			MappingDocument mappingDocument,
			Callback callback,
			List<?> jaxbAttributeMappings) {
		for ( Object jaxbAttributeMapping : jaxbAttributeMappings ) {
			if ( JaxbHbmCompositeKeyBasicAttributeType.class.isInstance( jaxbAttributeMapping ) ) {
				callback.addAttributeSource(
						new CompositeIdentifierSingularAttributeSourceBasicImpl(
								mappingDocument,
								callback.getAttributeSourceContainer(),
								(JaxbHbmCompositeKeyBasicAttributeType) jaxbAttributeMapping
						)
				);
			}
			else if ( JaxbHbmCompositeKeyManyToOneType.class.isInstance( jaxbAttributeMapping ) ) {
				callback.addAttributeSource(
						new CompositeIdentifierSingularAttributeSourceManyToOneImpl(
								mappingDocument,
								callback.getAttributeSourceContainer(),
								(JaxbHbmCompositeKeyManyToOneType) jaxbAttributeMapping
						)
				);
			}
			else {
				throw new MappingException(
						"Unexpected composite-key sub-attribute type : " + jaxbAttributeMapping.getClass().getName(),
						mappingDocument.getOrigin()
				);
			}
		}
	}
