	@Override
	protected Map buildSettings() {
		Map settings = super.buildSettings();
		if ( getDialect().getClass().equals( H2Dialect.class ) ) {
			settings.put(
					AvailableSettings.HBM2DDL_IMPORT_FILES,
					"schema-generation.sql"
			);
			settings.put( org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO, "update" );
		}
		return settings;
	}
