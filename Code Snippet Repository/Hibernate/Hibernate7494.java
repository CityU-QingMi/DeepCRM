	@Test
	public void testEnumString() {
		EntityConverter entity = new EntityConverter();
		entity.setLetterString( Letter.C );
		save( entity );

		entity = find( entity.getId(), "e.letterString='" + Letter.C.name() + "'" );

		assertNotNull( entity );
		assertEquals( Letter.C, entity.getLetterString() );
	}
