	@Override
	public Identifier determinePrimaryTableName(ImplicitEntityNameSource source) {
		if ( source == null ) {
			// should never happen, but to be defensive...
			throw new HibernateException( "Entity naming information was not provided." );
		}

		String tableName = transformEntityName( source.getEntityNaming() );

		if ( tableName == null ) {
			// todo : add info to error message - but how to know what to write since we failed to interpret the naming source
			throw new HibernateException( "Could not determine primary table name for entity" );
		}

		return toIdentifier( tableName, source.getBuildingContext() );
	}
