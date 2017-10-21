	@Override
	public void afterStatementExecution() {
		LOG.tracev( "Starting after statement execution processing [{0}]", getConnectionReleaseMode() );
		if ( getConnectionReleaseMode() == ConnectionReleaseMode.AFTER_STATEMENT ) {
			if ( ! releasesEnabled ) {
				LOG.debug( "Skipping aggressive release due to manual disabling" );
				return;
			}
			if ( hasRegisteredResources() ) {
				LOG.debug( "Skipping aggressive release due to registered resources" );
				return;
			}
			getLogicalConnection().afterStatement();
		}
	}
