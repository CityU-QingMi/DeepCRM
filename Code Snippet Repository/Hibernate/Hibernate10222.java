	@Override
	protected void addSettings(Map settings) {
		super.addSettings( settings );

		settings.put( EnversSettings.USE_REVISION_ENTITY_WITH_NATIVE_ID, "false" );

		if ( getAuditStrategy() != null ) {
			settings.put( EnversSettings.AUDIT_STRATEGY, getAuditStrategy() );
		}
	}
