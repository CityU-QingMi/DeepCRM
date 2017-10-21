	@BeforeClassOnce
	public void setUp() {
		ssr = new StandardServiceRegistryBuilder().build();

		metadata = new MetadataSources( ssr )
				.addAnnotatedClass( Forest.class )
				.addAnnotatedClass( Forest2.class )
				.addPackage( Forest.class.getPackage().getName() )
				.buildMetadata();
	}
