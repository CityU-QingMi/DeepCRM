	@Override
	protected void configure(Configuration configuration) {
		try {
			output = File.createTempFile( "update_script", ".sql" );
		}
		catch (IOException e) {
			fail( e.getMessage() );
		}
		output.deleteOnExit();
		configuration.setProperty( Environment.HBM2DDL_DATABASE_ACTION, "update" );
		configuration.setProperty( Environment.HBM2DDL_SCRIPTS_ACTION, "update" );
		configuration.setProperty( Environment.HBM2DDL_SCRIPTS_CREATE_TARGET, output.getAbsolutePath() );
	}
