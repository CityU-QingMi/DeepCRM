	@Test
	public void testPropertyBasedDiscriminatorForcing() throws Exception {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			Metadata metadata = new MetadataSources( ssr )
					.addAnnotatedClass( BaseClass2.class )
					.addAnnotatedClass( SubClass2.class )
					.getMetadataBuilder()
					.enableImplicitForcingOfDiscriminatorsInSelect( true )
					.build();

			PersistentClass persistentClass = metadata.getEntityBinding( BaseClass2.class.getName() );
			assertNotNull( persistentClass );
			assertTrue( persistentClass instanceof RootClass );

			RootClass root = ( RootClass ) persistentClass;
			assertTrue( "Discriminator should be forced by property", root.isForceDiscriminator() );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
