	@Test
	public void testNonDefaultOptions() throws Exception {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			Metadata metadata = new MetadataSources( ssr )
					.addAnnotatedClass( BaseClass.class )
					.addAnnotatedClass( SubClass.class )
					.buildMetadata();

			PersistentClass persistentClass = metadata.getEntityBinding( BaseClass.class.getName() );
			assertNotNull( persistentClass );
			assertTrue( persistentClass instanceof RootClass );

			RootClass root = (RootClass) persistentClass;
			assertTrue( "Discriminator should be forced", root.isForceDiscriminator() );
			assertFalse( "Discriminator should not be insertable", root.isDiscriminatorInsertable() );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
