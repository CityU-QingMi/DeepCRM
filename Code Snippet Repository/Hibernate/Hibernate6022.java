	@SuppressWarnings("")
	private Map buildSettingsMap() {
		Map settings = new HashMap();

		settings.put( AvailableSettings.LOADED_CLASSES, Collections.singletonList( Message.class ) );

		settings.put( org.hibernate.cfg.AvailableSettings.DIALECT, DerbyTenSevenDialect.class.getName() );
		settings.put( org.hibernate.cfg.AvailableSettings.DRIVER, org.apache.derby.jdbc.EmbeddedDriver.class.getName() );
		settings.put( org.hibernate.cfg.AvailableSettings.URL, "jdbc:derby:memory:hibernate-orm-testing;create=true" );
		settings.put( org.hibernate.cfg.AvailableSettings.USER, "" );

		settings.put( org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO, "create-drop" );
		return settings;
	}
