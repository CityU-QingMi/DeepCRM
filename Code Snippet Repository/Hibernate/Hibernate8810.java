	@BeforeClassOnce
	public void setUp() {
		ssr = new StandardServiceRegistryBuilder().build();
		metadata = (MetadataImplementor) new MetadataSources( ssr )
				.addAnnotatedClass( Category.class )
				.addAnnotatedClass( Item.class )
				.addAnnotatedClass( Workflow.class )
				.getMetadataBuilder()
				.applyImplicitNamingStrategy( new MyNamingStrategy() )
				.build();
	}
