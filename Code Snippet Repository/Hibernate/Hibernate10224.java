	private Map buildSettings() {
		Map settings = getConfig();
		addMappings( settings );

		if ( createSchema() ) {
			settings.put( org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO, "create-drop" );
			final String secondSchemaName = createSecondSchema();
			if ( StringHelper.isNotEmpty( secondSchemaName ) ) {
				if ( !(getDialect() instanceof H2Dialect) ) {
					throw new UnsupportedOperationException( "Only H2 dialect supports creation of second schema." );
				}
				Helper.createH2Schema( secondSchemaName, settings );
			}
		}

		if ( StringHelper.isNotEmpty( getAuditStrategy() ) ) {
			settings.put( EnversSettings.AUDIT_STRATEGY, getAuditStrategy() );
		}

		if ( !autoRegisterListeners() ) {
			settings.put( EnversIntegrator.AUTO_REGISTER, "false" );
		}

		settings.put( EnversSettings.USE_REVISION_ENTITY_WITH_NATIVE_ID, "false" );

		settings.put( org.hibernate.cfg.AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "true" );
		settings.put( org.hibernate.cfg.AvailableSettings.DIALECT, getDialect().getClass().getName() );
		return settings;
	}
