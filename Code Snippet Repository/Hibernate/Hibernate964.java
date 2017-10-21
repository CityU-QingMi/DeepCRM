	public static ResultSetMappingDefinition bind(
			ResultSetMappingBindingDefinition resultSetMappingSource,
			HbmLocalMetadataBuildingContext context) {
		if ( resultSetMappingSource.getName() == null ) {
			throw new MappingException(
					"ResultSet mapping did not specify name",
					context.getOrigin()
			);
		}

		final ResultSetMappingDefinition binding = new ResultSetMappingDefinition( resultSetMappingSource.getName() );
		bind( resultSetMappingSource, binding, context );
		return binding;
	}
