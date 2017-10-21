	@Test
	public void testHasChangedOnDoubleFlush() {
		List list = queryForPropertyHasChanged( BasicTestEntity1.class, id, "str1" );
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 2 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( BasicTestEntity1.class, id, "long1" );
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 2 ), extractRevisionNumbers( list ) );
	}
