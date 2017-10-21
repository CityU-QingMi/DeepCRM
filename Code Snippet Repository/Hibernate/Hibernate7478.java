	@Test
	public void testDefinitionAtEntityLevel() {
		// NOTE : simple-override2.xml applied disable-conversion="true" at the entity-level
		Metadata metadata = new MetadataSources( ssr )
				.addAnnotatedClass( TheEntity2.class )
				.addResource( "org/hibernate/test/converter/simple-override2.xml" )
				.buildMetadata();

		PersistentClass pc = metadata.getEntityBinding( TheEntity2.class.getName() );
		Type type = pc.getProperty( "it" ).getType();
		assertTyping( StringType.class, type );
	}
