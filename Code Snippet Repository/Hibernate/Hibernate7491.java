	@Test
	public void testStringWrapper() {
		EntityConverter entity = new EntityConverter();
		entity.setStringWrapper( new StringWrapper( "TEN" ) );
		save( entity );

		entity = find( entity.getId(), "e.stringWrapper='TEN'" );

		assertNotNull( entity );
		assertEquals( "TEN", entity.getStringWrapper().getValue() );
	}
