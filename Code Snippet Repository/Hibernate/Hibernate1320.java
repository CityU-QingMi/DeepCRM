	protected final Configuration buildConfiguration() {

		Configuration cfg = new Configuration().setProperties( buildProperties() );


		String[] mappingFiles = ConfigurationHelper.toStringArray( mapResources, " ,\n\t\r\f" );
		for ( int i = 0; i < mappingFiles.length; i++ ) {
			cfg.addResource( mappingFiles[i] );
		}

		return cfg;
	}
