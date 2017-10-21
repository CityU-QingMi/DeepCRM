	@Test
	public void testOrmXmlDefinedEnumType() {
		StandardServiceRegistry ssr = ServiceRegistryBuilder.buildServiceRegistry();

		try {
			MetadataSources ms = new MetadataSources( ssr );
			ms.addResource( "org/hibernate/test/annotations/enumerated/ormXml/orm.xml" );

			Metadata metadata = ms.buildMetadata();

			Type bindingPropertyType = metadata.getEntityBinding( BookWithOrmEnum.class.getName() )
					.getProperty( "bindingStringEnum" )
					.getType();
			CustomType customType = ExtraAssertions.assertTyping( CustomType.class, bindingPropertyType );
			EnumType enumType = ExtraAssertions.assertTyping( EnumType.class, customType.getUserType() );
			assertFalse( enumType.isOrdinal() );
		}
		finally {
			ServiceRegistryBuilder.destroy( ssr );
		}
	}
