	@Before
	public void setUp() {
		String poolName = "pool-one";

		properties = new Properties();
		properties.put( AvailableSettings.PROXOOL_POOL_ALIAS, poolName );
		properties.put( AvailableSettings.PROXOOL_PROPERTIES, poolName + ".properties" );

		ssr = new StandardServiceRegistryBuilder()
				.applySettings( properties )
				.build();
	}
