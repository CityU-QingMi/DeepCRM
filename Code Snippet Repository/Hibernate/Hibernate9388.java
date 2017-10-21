	@Test
	public void testBidirectionalOneToManyReferencingRootEntity() throws Exception {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
				.addAnnotatedClass( Step.class )
				.addAnnotatedClass( GroupStep.class )
				.buildMetadata();
		metadata.validate();

		try {
			try {
				new SchemaUpdate().execute( EnumSet.of( TargetType.DATABASE ), metadata );
			}
			finally {
				new SchemaExport().drop( EnumSet.of( TargetType.DATABASE ), metadata );
			}
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
