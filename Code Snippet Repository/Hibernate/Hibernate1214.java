	private static void processId(
			PropertyHolder propertyHolder,
			PropertyData inferredData,
			SimpleValue idValue,
			HashMap<String, IdentifierGeneratorDefinition> classGenerators,
			boolean isIdentifierMapper,
			MetadataBuildingContext buildingContext) {
		if ( isIdentifierMapper ) {
			throw new AnnotationException(
					"@IdClass class should not have @Id nor @EmbeddedId properties: "
							+ BinderHelper.getPath( propertyHolder, inferredData )
			);
		}
		XClass returnedClass = inferredData.getClassOrElement();
		XProperty property = inferredData.getProperty();
		//clone classGenerator and override with local values
		HashMap<String, IdentifierGeneratorDefinition> localGenerators = ( HashMap<String, IdentifierGeneratorDefinition> ) classGenerators.clone();
		localGenerators.putAll( buildLocalGenerators( property, buildingContext ) );

		//manage composite related metadata
		//guess if its a component and find id data access (property, field etc)
		final boolean isComponent = returnedClass.isAnnotationPresent( Embeddable.class )
				|| property.isAnnotationPresent( EmbeddedId.class );

		GeneratedValue generatedValue = property.getAnnotation( GeneratedValue.class );
		String generatorType = generatedValue != null
				? generatorType( generatedValue.strategy(), buildingContext, returnedClass )
				: "assigned";
		String generatorName = generatedValue != null
				? generatedValue.generator()
				: BinderHelper.ANNOTATION_STRING_DEFAULT;
		if ( isComponent ) {
			//a component must not have any generator
			generatorType = "assigned";
		}
		BinderHelper.makeIdGenerator( idValue, generatorType, generatorName, buildingContext, localGenerators );

		if ( LOG.isTraceEnabled() ) {
			LOG.tracev( "Bind {0} on {1}", ( isComponent ? "@EmbeddedId" : "@Id" ), inferredData.getPropertyName() );
		}
	}
