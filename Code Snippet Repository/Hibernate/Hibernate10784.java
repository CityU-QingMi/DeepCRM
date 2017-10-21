	@Test
	public void testHasChangedId4() throws Exception {
		List list = queryForPropertyHasChangedWithDeleted( ComponentTestEntity.class, id4, "comp1" );
		assertEquals( 2, list.size() );
		assertEquals( makeList( 2, 3 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChangedWithDeleted( ComponentTestEntity.class, id4, "comp1" );
		assertEquals( 1, list.size() );
		assertEquals( makeList( 1 ), extractRevisionNumbers( list ) );
	}
