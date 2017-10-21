	@Before
	public void setUp() throws IOException {
		ssr = new StandardServiceRegistryBuilder()
				.applySetting( AvailableSettings.KEYWORD_AUTO_QUOTING_ENABLED, "true" )
				.applySetting( AvailableSettings.HBM2DDL_JDBC_METADATA_EXTRACTOR_STRATEGY, jdbcMetadataExtractorStrategy )
				.build();
		output = File.createTempFile( "update_script", ".sql" );
		output.deleteOnExit();

		metadata = new MetadataSources( ssr )
				.addAnnotatedClass( Employee.class )
				.buildMetadata();
		new SchemaExport().create( EnumSet.of( TargetType.DATABASE ), metadata );
	}
