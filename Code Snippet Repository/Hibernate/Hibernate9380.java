	@Before
	public void setUp() throws IOException {
		final StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
		standardServiceRegistryBuilder.applySetting(
				org.hibernate.cfg.AvailableSettings.KEYWORD_AUTO_QUOTING_ENABLED,
				"true"
		);
		ssr = standardServiceRegistryBuilder.build();
		final MetadataSources metadataSources = new MetadataSources( ssr );

		metadataSources.addAnnotatedClass( Match.class );
		metadata = (MetadataImplementor) metadataSources.buildMetadata();
		metadata.validate();

		new SchemaExport().setHaltOnError( true )
				.setFormat( false )
				.createOnly( EnumSet.of( TargetType.DATABASE ), metadata );
	}
