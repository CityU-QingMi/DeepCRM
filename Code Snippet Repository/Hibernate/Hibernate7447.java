	@Test
	public void testNonAutoApplyHandling() {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
					.addAnnotatedClass( Tester.class )
					.getMetadataBuilder()
					.applyAttributeConverter( NotAutoAppliedConverter.class, false )
					.build();

			PersistentClass tester = metadata.getEntityBinding( Tester.class.getName() );
			Property nameProp = tester.getProperty( "name" );
			SimpleValue nameValue = (SimpleValue) nameProp.getValue();
			Type type = nameValue.getType();
			assertNotNull( type );
			if ( AttributeConverterTypeAdapter.class.isInstance( type ) ) {
				fail( "AttributeConverter with autoApply=false was auto applied" );
			}
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}

	}
