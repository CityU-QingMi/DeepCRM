	@Before
	public void setUp() {
		serviceRegistry = new StandardServiceRegistryBuilder().applySetting(
				Environment.GLOBALLY_QUOTED_IDENTIFIERS,
				"false"
		).build();
		metadata = (MetadataImplementor) new MetadataSources( serviceRegistry )
				.addAnnotatedClass( MyEntity.class )
				.buildMetadata();

		System.out.println( "********* Starting SchemaExport for START-UP *************************" );
		new SchemaExport().create( EnumSet.of( TargetType.STDOUT, TargetType.DATABASE ), metadata );
		System.out.println( "********* Completed SchemaExport for START-UP *************************" );
	}
