	@Test
	public void testChildHasChanged() throws Exception {
		List list = queryForPropertyHasChanged( ChildEntity.class, id1, "data" );
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 2 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( ChildEntity.class, id1, "numVal" );
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 2 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged( ChildEntity.class, id1, "data" );
		assertEquals( 0, list.size() );

		list = queryForPropertyHasNotChanged( ChildEntity.class, id1, "numVal" );
		assertEquals( 0, list.size() );
	}
