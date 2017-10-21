	public static ResultSetMappingDefinition bind(
			ResultSetMappingBindingDefinition resultSetMappingSource,
			HbmLocalMetadataBuildingContext context,
			String prefix) {
		if ( StringHelper.isEmpty( prefix ) ) {
			throw new AssertionFailure( "Passed prefix was null; perhaps you meant to call the alternate #bind form?" );
		}

		final String resultSetName = prefix + '.' + resultSetMappingSource.getName();
		final ResultSetMappingDefinition binding = new ResultSetMappingDefinition( resultSetName );
		bind( resultSetMappingSource, binding, context );
		return binding;
	}
