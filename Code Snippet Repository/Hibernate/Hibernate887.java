	public static void processIdentifierGeneratorDefinition(
			HbmLocalMetadataBuildingContext context,
			JaxbHbmIdentifierGeneratorDefinitionType identifierGenerator) {
		log.debugf( "Processing <identifier-generator/> : %s", identifierGenerator.getName() );

		context.getMetadataCollector().addIdentifierGenerator(
				new IdentifierGeneratorDefinition(
						identifierGenerator.getName(),
						identifierGenerator.getClazz()
				)
		);
	}
