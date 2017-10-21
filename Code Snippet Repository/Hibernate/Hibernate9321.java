	@Before
	public void setUp() {
		connectionProvider = new ConnectionProviderDecorator();
		connectionProvider.configure( getConnectionProviderProperties() );

		ssr = new StandardServiceRegistryBuilder()
				.addService( ConnectionProvider.class, connectionProvider )
				.applySetting(Environment.DIALECT, H2Dialect.class.getName())
				.build();
		metadata = (MetadataImplementor) new MetadataSources( ssr )
				.addAnnotatedClass( Thing.class )
				.buildMetadata();
		metadata.validate();
	}
