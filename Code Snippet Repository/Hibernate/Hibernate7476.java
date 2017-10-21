	@Test
	public void baseline() {
		Metadata metadata = new MetadataSources( ssr )
				.addAnnotatedClass( TheEntity.class )
				.buildMetadata();

		PersistentClass pc = metadata.getEntityBinding( TheEntity.class.getName() );
		Type type = pc.getProperty( "it" ).getType();
		AttributeConverterTypeAdapter adapter = assertTyping( AttributeConverterTypeAdapter.class, type );
		assertTyping( SillyStringConverter.class, adapter.getAttributeConverter() );
	}
