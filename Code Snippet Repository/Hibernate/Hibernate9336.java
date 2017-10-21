	@Before
	public void setUp() {
		serviceRegistry = ServiceRegistryBuilder.buildServiceRegistry( Environment.getProperties() );
        metadata = (MetadataImplementor) new MetadataSources( serviceRegistry )
                .addResource( "org/hibernate/test/schemaupdate/mapping.hbm.xml" )
                .buildMetadata();
        metadata.validate();

		new SchemaExport().drop( EnumSet.of( TargetType.DATABASE, TargetType.STDOUT ), metadata );
	}
