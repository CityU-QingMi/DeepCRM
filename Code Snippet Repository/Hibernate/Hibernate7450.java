	@Test
	public void testBasicConverterDisableApplication() {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
					.addAnnotatedClass( Tester2.class )
					.getMetadataBuilder()
					.applyAttributeConverter( StringClobConverter.class, true )
					.build();

			PersistentClass tester = metadata.getEntityBinding( Tester2.class.getName() );
			Property nameProp = tester.getProperty( "name" );
			SimpleValue nameValue = (SimpleValue) nameProp.getValue();
			Type type = nameValue.getType();
			assertNotNull( type );
			if ( AttributeConverterTypeAdapter.class.isInstance( type ) ) {
				fail( "AttributeConverter applied (should not have been)" );
			}
			AbstractStandardBasicType basicType = assertTyping( AbstractStandardBasicType.class, type );
			assertSame( StringTypeDescriptor.INSTANCE, basicType.getJavaTypeDescriptor() );
			assertEquals( Types.VARCHAR, basicType.getSqlTypeDescriptor().getSqlType() );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
