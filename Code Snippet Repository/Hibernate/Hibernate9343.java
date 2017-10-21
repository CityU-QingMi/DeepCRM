	@Before
	public void setUp() {
		serviceRegistry = new StandardServiceRegistryBuilder().applySetting(
				Environment.GLOBALLY_QUOTED_IDENTIFIERS,
				"true"
		).build();
		metadata = (MetadataImplementor) new MetadataSources( serviceRegistry )
				.addAnnotatedClass( MyEntity.class )
				.addAnnotatedClass( Role.class )
				.buildMetadata();

		System.out.println( "********* Starting SchemaExport for START-UP *************************" );
		new SchemaExport().create( EnumSet.of( TargetType.STDOUT, TargetType.DATABASE ), metadata );
		System.out.println( "********* Completed SchemaExport for START-UP *************************" );
	}
