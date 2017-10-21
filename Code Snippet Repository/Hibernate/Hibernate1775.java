	private void registerDefaultProperties() {
		// true, false and unknown are now valid values
		// Remove the query substitutions previously added in IngresDialect.
		final Properties properties = getDefaultProperties();
		final String querySubst = properties.getProperty( Environment.QUERY_SUBSTITUTIONS );
		if ( querySubst != null ) {
			final String newQuerySubst = querySubst.replace( "true=1,false=0", "" );
			properties.setProperty( Environment.QUERY_SUBSTITUTIONS, newQuerySubst );
		}
	}
