	private void prepareForNamedParameters() {
		if ( parameterStrategy == ParameterStrategy.POSITIONAL ) {
			throw new QueryException( "Cannot mix named and positional parameters" );
		}
		if ( parameterStrategy == ParameterStrategy.UNKNOWN ) {
			// protect to only do this check once
			final ExtractedDatabaseMetaData databaseMetaData = getSession()
					.getJdbcCoordinator()
					.getJdbcSessionOwner()
					.getJdbcSessionContext()
					.getServiceRegistry().getService( JdbcEnvironment.class )
					.getExtractedDatabaseMetaData();
			if ( ! databaseMetaData.supportsNamedParameters() ) {
				LOG.unsupportedNamedParameters();
			}
			parameterStrategy = ParameterStrategy.NAMED;
		}
	}
