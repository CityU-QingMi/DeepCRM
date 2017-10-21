	@Test
	@TestForIssue(jiraKey = "")
	public void testBasicOrmXmlConverterApplication() {
		final StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().build();

		try {
			MetadataImplementor metadata = (MetadataImplementor) new MetadataSources( ssr )
					.addAnnotatedClass( Tester.class )
					.addURL( ConfigHelper.findAsResource( "org/hibernate/test/converter/orm.xml" ) )
					.getMetadataBuilder()
					.build();

			PersistentClass tester = metadata.getEntityBinding( Tester.class.getName() );
			Property nameProp = tester.getProperty( "name" );
			SimpleValue nameValue = (SimpleValue) nameProp.getValue();
			Type type = nameValue.getType();
			assertNotNull( type );
			if ( !AttributeConverterTypeAdapter.class.isInstance( type ) ) {
				fail( "AttributeConverter not applied" );
			}
			AttributeConverterTypeAdapter basicType = assertTyping( AttributeConverterTypeAdapter.class, type );
			assertSame( StringTypeDescriptor.INSTANCE, basicType.getJavaTypeDescriptor() );
			assertEquals( Types.CLOB, basicType.getSqlTypeDescriptor().getSqlType() );
		}
		finally {
			StandardServiceRegistryBuilder.destroy( ssr );
		}
	}
