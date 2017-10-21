	private static String generatorType(
			GenerationType generatorEnum,
			final MetadataBuildingContext buildingContext,
			final XClass javaTypeXClass) {
		return buildingContext.getBuildingOptions().getIdGenerationTypeInterpreter().determineGeneratorName(
				generatorEnum,
				new IdGeneratorStrategyInterpreter.GeneratorNameDeterminationContext() {
					Class javaType = null;
					@Override
					public Class getIdType() {
						if ( javaType == null ) {
							javaType = buildingContext.getBuildingOptions()
									.getReflectionManager()
									.toClass( javaTypeXClass );
						}
						return javaType;
					}
				}
		);
	}
