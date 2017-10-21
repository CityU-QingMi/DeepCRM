	@Test
	public void testEntityWithMultipleJoinFetchedBags() {
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().build();

		Metadata metadata = new MetadataSources( standardRegistry )
				.addAnnotatedClass( Post.class )
				.addAnnotatedClass( PostComment.class )
				.addAnnotatedClass( Tag.class )
				.getMetadataBuilder()
				.build();
		try {
			metadata.buildSessionFactory();
			fail( "MultipleBagFetchException should have been thrown." );
		}
		catch (MultipleBagFetchException expected) {
		}
	}
