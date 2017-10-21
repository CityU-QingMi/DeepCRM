	@Test
	public void testBasicOperation() {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr ).buildMetadata();
			SimpleValue simpleValue = new SimpleValue( metadata );
			simpleValue.setJpaAttributeConverterDescriptor(
					new AttributeConverterDescriptorNonAutoApplicableImpl( new StringClobConverter() )
			);
			simpleValue.setTypeUsingReflection( IrrelevantEntity.class.getName(), "name" );

			Type type = simpleValue.getType();
			assertNotNull( type );
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
