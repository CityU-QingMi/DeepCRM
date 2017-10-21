	@Before
	public void setUp() throws IOException {
		output = File.createTempFile( "update_script", ".sql" );
		output.deleteOnExit();
		ssr = new StandardServiceRegistryBuilder().build();

		final MetadataSources metadataSources = new MetadataSources( ssr )
				.addAnnotatedClass( From.class );
		metadata = (MetadataImplementor) metadataSources.buildMetadata();
	}
