	@Test
	public void testEnumOrdinal() {
		EntityConverter entity = new EntityConverter();
		entity.setLetterOrdinal( Letter.B );
		save( entity );

		entity = find( entity.getId(), "e.letterOrdinal=" + Letter.B.ordinal() );

		assertNotNull( entity );
		assertEquals( Letter.B, entity.getLetterOrdinal() );
	}
