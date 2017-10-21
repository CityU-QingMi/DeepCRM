	@Test
	@Priority(6)
	public void testEnumRepresentation() {
		Session session = getSession();

		@SuppressWarnings("unchecked")
		List<Object[]> values = session
				.createSQLQuery( "SELECT enum1, enum2 FROM ENUM_ENTITY_AUD ORDER BY rev ASC" )
				.list();
		session.close();

		Assert.assertNotNull( values );
		Assert.assertEquals( 2, values.size() );
		Assert.assertArrayEquals( new Object[]{ 0, 0 }, values.get( 0 ) );
		Assert.assertArrayEquals( new Object[]{ 1, 1 }, values.get( 1 ) );
	}
