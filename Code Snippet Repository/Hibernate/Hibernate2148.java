	@Override
	public String toMetaDataSchemaName(Identifier identifier) {
		log.tracef( "Normalizing identifier quoting for schema name [%s]", identifier );

		if ( !nameQualifierSupport.supportsSchemas() ) {
			// null is used to tell DatabaseMetaData to not limit results based on schema.
			log.trace( "Environment does not support catalogs; returning null" );
			return null;
		}

		if ( identifier == null ) {
			if ( jdbcEnvironment.getCurrentSchema() == null ) {
				return "";
			}
			identifier = jdbcEnvironment.getCurrentSchema();
		}

		return toMetaDataText( identifier );
	}
