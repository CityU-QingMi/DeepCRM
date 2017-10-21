	@Test
	public void testMappingAttributeWithLobAndAttributeConverter() {
		final Metadata metadata = new MetadataSources( ssr )
				.addAnnotatedClass( EntityImpl.class )
				.buildMetadata();

		final Type type = metadata.getEntityBinding( EntityImpl.class.getName() ).getProperty( "status" ).getType();
		final AttributeConverterTypeAdapter concreteType = assertTyping( AttributeConverterTypeAdapter.class, type );
		assertEquals( Types.BLOB, concreteType.getSqlTypeDescriptor().getSqlType() );
	}
