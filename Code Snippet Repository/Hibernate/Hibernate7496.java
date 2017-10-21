	@Test
	public void testNumberImplicitOverrided() {
		EntityConverter entity = new EntityConverter();
		entity.setNumbersImplicitOverrided( Numbers.TWO );
		save( entity );

		entity = find( entity.getId(), "e.numbersImplicitOverrided='" + ( Numbers.TWO.ordinal() + 1 ) + "'" );

		assertNotNull( entity );
		assertEquals( Numbers.TWO, entity.getNumbersImplicitOverrided() );
	}
