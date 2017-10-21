	@Override
	public void afterStatement() {
		super.afterStatement();

		if ( connectionHandlingMode.getReleaseMode() == ConnectionReleaseMode.AFTER_STATEMENT ) {
			if ( getResourceRegistry().hasRegisteredResources() ) {
				log.debug( "Skipping aggressive release of JDBC Connection after-statement due to held resources" );
			}
			else {
				log.debug( "Initiating JDBC connection release from afterStatement" );
				releaseConnection();
			}
		}
	}
