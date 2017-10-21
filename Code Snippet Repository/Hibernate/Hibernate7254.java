	@Test
	public void testNoCircularityDetection() {
		StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			final Metadata metadata = new MetadataSources( ssr )
					.addAnnotatedClass( Entity1.class )
					.addAnnotatedClass( Entity2.class )
					.buildMetadata();


			org.hibernate.mapping.Table entity1Table = metadata.getEntityBinding( Entity1.class.getName() ).getTable();
			org.hibernate.mapping.Table entity2Table = metadata.getEntityBinding( Entity2.class.getName() ).getTable();

			assertTrue( entity1Table.getName().equals( entity2Table.getName() ) );
			assertFalse( entity1Table.getSchema().equals( entity2Table.getSchema() ) );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
