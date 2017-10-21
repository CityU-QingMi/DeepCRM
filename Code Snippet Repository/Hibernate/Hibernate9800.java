	@Override
	@SuppressWarnings("")
	protected void addSettings(Map settings) {
		super.addSettings( settings );
		if ( H2Dialect.class.equals( Dialect.getDialect().getClass() ) ) {
			settings.put( Environment.URL, "jdbc:h2:mem:db-mvcc;MVCC=true" );
		}
		else if( SQLServerDialect.class.isAssignableFrom( Dialect.getDialect().getClass() )) {
			connectionProvider = new SQLServerSnapshotIsolationConnectionProvider();
			settings.put( AvailableSettings.CONNECTION_PROVIDER, connectionProvider );
		}
		settings.put( AvailableSettings.GENERATE_STATISTICS, "true" );
	}
