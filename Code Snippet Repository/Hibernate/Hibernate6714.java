	@Test
	public void testBaseline() throws Exception {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			Metadata metadata = new MetadataSources( ssr )
					.addAnnotatedClass( BaseClass2.class )
					.addAnnotatedClass( SubClass2.class )
					.buildMetadata();

			PersistentClass persistentClass = metadata.getEntityBinding( BaseClass2.class.getName() );
			assertNotNull( persistentClass );
			assertTrue( persistentClass instanceof RootClass );

			RootClass root = ( RootClass ) persistentClass;
			assertFalse( "Discriminator should not be forced by default", root.isForceDiscriminator() );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
