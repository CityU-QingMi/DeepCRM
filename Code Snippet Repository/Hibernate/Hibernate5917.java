	@Test
	public void testEmbeddable() throws Exception {
		final EntityType<House> entityType = entityManagerFactory().getMetamodel().entity( House.class );
		final SingularAttribute<? super House,Address> address = entityType.getDeclaredSingularAttribute(
				"address",
				Address.class
		);
		assertNotNull( address );
		assertEquals( Attribute.PersistentAttributeType.EMBEDDED, address.getPersistentAttributeType() );
		assertFalse( address.isCollection() );
		assertFalse( address.isAssociation() );
		final EmbeddableType<Address> addressType = (EmbeddableType<Address>) address.getType();
		assertEquals( Type.PersistenceType.EMBEDDABLE, addressType.getPersistenceType() );
		assertEquals( 3, addressType.getDeclaredAttributes().size() );
		assertTrue( addressType.getDeclaredSingularAttribute( "address1" ).isOptional() );
		assertFalse( addressType.getDeclaredSingularAttribute( "address2" ).isOptional() );

		final EmbeddableType<Address> directType = entityManagerFactory().getMetamodel().embeddable( Address.class );
		assertNotNull( directType );
		assertEquals( Type.PersistenceType.EMBEDDABLE, directType.getPersistenceType() );
	}
