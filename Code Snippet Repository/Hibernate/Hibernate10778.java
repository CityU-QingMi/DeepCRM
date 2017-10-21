	@Test
	public void testReferencedEntityHasChanged() throws Exception {
		List list = queryForPropertyHasChanged( ReferencedEntity.class, re_id1, "referencing" );
		assertEquals( 2, list.size() );
		assertEquals( makeList( 2, 3 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged( ReferencedEntity.class, re_id1, "referencing" );
		assertEquals( 1, list.size() ); // initially referencing collection is null
		assertEquals( makeList( 1 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( ReferencedEntity.class, re_id2, "referencing" );
		assertEquals( 1, list.size() );
		assertEquals( makeList( 3 ), extractRevisionNumbers( list ) );
	}
