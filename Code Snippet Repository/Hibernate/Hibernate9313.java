	@Before
	public void setUp() {
		Map settings = new HashMap();
		settings.putAll( Environment.getProperties() );
		settings.put( AvailableSettings.DIALECT, H2Dialect.class.getName() );
		settings.put( AvailableSettings.FORMAT_SQL, false );

		this.serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( settings );

		MetadataSources ms = new MetadataSources( serviceRegistry );
		ms.addAnnotatedClass( Schema1Entity1.class );
		ms.addAnnotatedClass( Schema2Entity2.class );
		this.metadata = ms.buildMetadata();
	}
