	@Override
	protected void configure(Configuration cfg) {
		super.configure( cfg );
		Properties properties = Environment.getProperties();
		if ( H2Dialect.class.getName().equals( properties.get( Environment.DIALECT ) ) ) {
			cfg.setProperty( Environment.URL, "jdbc:h2:mem:db-mvcc;MVCC=true" );
		}
		cfg.setProperty( Environment.CACHE_REGION_PREFIX, "" );
		cfg.setProperty( Environment.GENERATE_STATISTICS, "true" );
		cfg.setProperty( Environment.USE_SECOND_LEVEL_CACHE, "true" );
		cfg.setProperty( Environment.CACHE_PROVIDER_CONFIG, "true" );
	}
