	@Test
	public void testIntegerWrapper() {
		EntityConverter entity = new EntityConverter();
		entity.setIntegerWrapper( new IntegerWrapper( 10 ) );
		save( entity );

		entity = find( entity.getId(), "e.integerWrapper=10" );

		assertNotNull( entity );
		assertEquals( 10, entity.getIntegerWrapper().getValue() );
	}
