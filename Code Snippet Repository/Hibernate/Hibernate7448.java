	@Test
	public void testBasicConverterApplication() {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
					.addAnnotatedClass( Tester.class )
					.getMetadataBuilder()
					.applyAttributeConverter( StringClobConverter.class, true )
					.build();

			PersistentClass tester = metadata.getEntityBinding( Tester.class.getName() );
			Property nameProp = tester.getProperty( "name" );
			SimpleValue nameValue = (SimpleValue) nameProp.getValue();
			Type type = nameValue.getType();
			assertNotNull( type );
			assertTyping( BasicType.class, type );
			if ( !AttributeConverterTypeAdapter.class.isInstance( type ) ) {
				fail( "AttributeConverter not applied" );
			}
			AbstractStandardBasicType basicType = assertTyping( AbstractStandardBasicType.class, type );
			assertSame( StringTypeDescriptor.INSTANCE, basicType.getJavaTypeDescriptor() );
			assertEquals( Types.CLOB, basicType.getSqlTypeDescriptor().getSqlType() );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
