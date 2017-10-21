	private static void bindTypeDef(TypeDef defAnn, MetadataBuildingContext context) {
		Properties params = new Properties();
		for ( Parameter param : defAnn.parameters() ) {
			params.setProperty( param.name(), param.value() );
		}

		if ( BinderHelper.isEmptyAnnotationValue( defAnn.name() ) && defAnn.defaultForType().equals( void.class ) ) {
			throw new AnnotationException(
					"Either name or defaultForType (or both) attribute should be set in TypeDef having typeClass " +
							defAnn.typeClass().getName()
			);
		}

		final String typeBindMessageF = "Binding type definition: %s";
		if ( !BinderHelper.isEmptyAnnotationValue( defAnn.name() ) ) {
			if ( LOG.isDebugEnabled() ) {
				LOG.debugf( typeBindMessageF, defAnn.name() );
			}
			context.getMetadataCollector().addTypeDefinition(
					new TypeDefinition(
							defAnn.name(),
							defAnn.typeClass(),
							null,
							params
					)
			);
		}

		if ( !defAnn.defaultForType().equals( void.class ) ) {
			if ( LOG.isDebugEnabled() ) {
				LOG.debugf( typeBindMessageF, defAnn.defaultForType().getName() );
			}
			context.getMetadataCollector().addTypeDefinition(
					new TypeDefinition(
							defAnn.defaultForType().getName(),
							defAnn.typeClass(),
							new String[]{ defAnn.defaultForType().getName() },
							params
					)
			);
		}

	}
