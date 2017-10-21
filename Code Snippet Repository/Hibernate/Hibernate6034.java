	@SuppressWarnings("")
	private Map buildSettingsMap() {
		Map settings = new HashMap();

		settings.put( AvailableSettings.LOADED_CLASSES, Collections.singletonList( User.class ) );

		settings.put( org.hibernate.cfg.AvailableSettings.DIALECT, DerbyTenSevenDialect.class );
		settings.put( org.hibernate.cfg.AvailableSettings.DRIVER,  org.apache.derby.jdbc.EmbeddedDriver.class.getName() );
//		settings.put( org.hibernate.cfg.AvailableSettings.URL, "jdbc:derby:/tmp/hibernate-orm-testing;create=true" );
		settings.put( org.hibernate.cfg.AvailableSettings.URL, "jdbc:derby:memory:hibernate-orm-testing;create=true" );
		settings.put( org.hibernate.cfg.AvailableSettings.USER, "" );

		settings.put( org.hibernate.cfg.AvailableSettings.HBM2DDL_AUTO, "create-drop" );
		settings.put( org.hibernate.cfg.AvailableSettings.USE_NEW_ID_GENERATOR_MAPPINGS, "true" );
		settings.put( org.hibernate.cfg.AvailableSettings.DIALECT, DerbyTenSevenDialect.class.getName() );
		return settings;
	}
