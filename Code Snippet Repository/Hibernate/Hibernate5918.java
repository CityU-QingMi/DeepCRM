	@Test
	public void testElementCollection() throws Exception {
		final EntityType<House> entityType = entityManagerFactory().getMetamodel().entity( House.class );
		final SetAttribute<House,Room> rooms = entityType.getDeclaredSet( "rooms", Room.class );
		assertNotNull( rooms );
		assertFalse( rooms.isAssociation() );
		assertTrue( rooms.isCollection() );
		assertEquals( Attribute.PersistentAttributeType.ELEMENT_COLLECTION, rooms.getPersistentAttributeType() );
		assertEquals( Room.class, rooms.getBindableJavaType() );
		assertEquals( Bindable.BindableType.PLURAL_ATTRIBUTE, rooms.getBindableType() );
		assertEquals( Set.class, rooms.getJavaType() );
		assertEquals( PluralAttribute.CollectionType.SET, rooms.getCollectionType() );
		assertEquals( 3, entityType.getDeclaredPluralAttributes().size() );
		assertEquals( Type.PersistenceType.EMBEDDABLE, rooms.getElementType().getPersistenceType() );

		final MapAttribute<House,String,Room> roomsByName = entityType.getDeclaredMap(
				"roomsByName", String.class, Room.class
		);
		assertNotNull( roomsByName );
		assertEquals( String.class, roomsByName.getKeyJavaType() );
		assertEquals( Type.PersistenceType.BASIC, roomsByName.getKeyType().getPersistenceType() );
		assertEquals( PluralAttribute.CollectionType.MAP, roomsByName.getCollectionType() );

		final ListAttribute<House,Room> roomsBySize = entityType.getDeclaredList( "roomsBySize", Room.class );
		assertNotNull( roomsBySize );
		assertEquals( Type.PersistenceType.EMBEDDABLE, roomsBySize.getElementType().getPersistenceType() );
		assertEquals( PluralAttribute.CollectionType.LIST, roomsBySize.getCollectionType() );
	}
