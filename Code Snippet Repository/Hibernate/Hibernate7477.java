	@Test
	public void testDefinitionAtAttributeLevel() {
		// NOTE : simple-override.xml applied disable-conversion="true" at the attribute-level
		Metadata metadata = new MetadataSources( ssr )
				.addAnnotatedClass( TheEntity.class )
				.addResource( "org/hibernate/test/converter/simple-override.xml" )
				.buildMetadata();

		PersistentClass pc = metadata.getEntityBinding( TheEntity.class.getName() );
		Type type = pc.getProperty( "it" ).getType();
		assertTyping( StringType.class, type );
	}
