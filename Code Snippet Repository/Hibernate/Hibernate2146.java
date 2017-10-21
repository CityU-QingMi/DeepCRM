	@Override
	public String toMetaDataCatalogName(Identifier identifier) {
		log.tracef( "Normalizing identifier quoting for catalog name [%s]", identifier );

		if ( !nameQualifierSupport.supportsCatalogs() ) {
			log.trace( "Environment does not support catalogs; returning null" );
			// null is used to tell DatabaseMetaData to not limit results based on catalog.
			return null;
		}

		if ( identifier == null ) {
			if ( jdbcEnvironment.getCurrentCatalog() == null ) {
				return "";
			}
			identifier = jdbcEnvironment.getCurrentCatalog();
		}

		return toMetaDataText( identifier );
	}
