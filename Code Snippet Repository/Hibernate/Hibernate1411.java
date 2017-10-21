	public static void bindNamedStoredProcedureQuery(
			NamedStoredProcedureQuery annotation,
			MetadataBuildingContext context,
			boolean isDefault) {
		if ( annotation == null ) {
			return;
		}

		if ( BinderHelper.isEmptyAnnotationValue( annotation.name() ) ) {
			throw new AnnotationException( "A named query must have a name when used in class or package level" );
		}

		final NamedProcedureCallDefinition def = new NamedProcedureCallDefinition( annotation );

		if (isDefault) {
			context.getMetadataCollector().addDefaultNamedProcedureCallDefinition( def );
		}
		else {
			context.getMetadataCollector().addNamedProcedureCallDefinition( def );
		}
		LOG.debugf( "Bound named stored procedure query : %s => %s", def.getRegisteredName(), def.getProcedureName() );
	}
