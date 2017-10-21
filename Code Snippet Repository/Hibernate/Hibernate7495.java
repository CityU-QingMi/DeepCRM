	@Test
	public void testNumberImplicit() {
		EntityConverter entity = new EntityConverter();
		entity.setNumbersImplicit( Numbers.THREE );
		save( entity );

		entity = find( entity.getId(), "e.numbersImplicit=" + ( Numbers.THREE.ordinal() + 1 ) );

		assertNotNull( entity );
		assertEquals( Numbers.THREE, entity.getNumbersImplicit() );
	}
