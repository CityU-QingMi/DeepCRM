	static IdentifierGeneratorDefinition interpretGeneratorDefinition(
			MappingDocument mappingDocument,
			EntityNamingSource entityNaming,
			JaxbHbmGeneratorSpecificationType jaxbGeneratorMapping) {
		if ( jaxbGeneratorMapping == null ) {
			return null;
		}

		final String generatorName = jaxbGeneratorMapping.getClazz();
		IdentifierGeneratorDefinition identifierGeneratorDefinition = mappingDocument.getMetadataCollector()
				.getIdentifierGenerator( generatorName );
		if ( identifierGeneratorDefinition == null ) {
			identifierGeneratorDefinition = new IdentifierGeneratorDefinition(
					entityNaming.getEntityName() + '.' + generatorName,
					generatorName,
					Helper.extractParameters( jaxbGeneratorMapping.getConfigParameters() )
			);
		}
		return identifierGeneratorDefinition;
	}
