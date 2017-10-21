	public static IdentifierGeneratorDefinition getIdentifierGenerator(
			String name,
			Map<String, IdentifierGeneratorDefinition> localGenerators,
			MetadataBuildingContext buildingContext) {
		if ( localGenerators != null ) {
			final IdentifierGeneratorDefinition result = localGenerators.get( name );
			if ( result != null ) {
				return result;
			}
		}

		return buildingContext.getMetadataCollector().getIdentifierGenerator( name );
	}
