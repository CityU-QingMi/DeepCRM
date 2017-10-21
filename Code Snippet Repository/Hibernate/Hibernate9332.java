	@Test
	public void testGeneratingUpdateScript() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();
		try {

			final MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
					.addResource( "org/hibernate/test/schemaupdate/UserGroup.hbm.xml" )
					.buildMetadata();
			new SchemaUpdate().execute( EnumSet.of( TargetType.STDOUT ), metadata );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
