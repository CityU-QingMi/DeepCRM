	@Override
	public void addResultSetMapping(ResultSetMappingDefinition resultSetMappingDefinition) {
		if ( resultSetMappingDefinition == null ) {
			throw new IllegalArgumentException( "Result-set mapping was null" );
		}

		final String name = resultSetMappingDefinition.getName();
		if ( name == null ) {
			throw new IllegalArgumentException( "Result-set mapping name is null: " + resultSetMappingDefinition );
		}

		if ( defaultSqlResultSetMappingNames.contains( name ) ) {
			return;
		}

		applyResultSetMapping( resultSetMappingDefinition );
	}
