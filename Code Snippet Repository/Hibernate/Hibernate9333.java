	@After
	public void tearDown() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();
		try {

			final MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
					.addResource( "org/hibernate/test/schemaupdate/UserGroup.hbm.xml" )
					.buildMetadata();
			new SchemaExport().drop( EnumSet.of( TargetType.STDOUT, TargetType.DATABASE ), metadata );

		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
