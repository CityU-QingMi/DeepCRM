	@Test
	public void testHasChanged() throws Exception {
		List list = queryForPropertyHasChanged(
				BiRefEdEntity.class, ed1_id,
				"referencing"
		);
		assertEquals( 3, list.size() );
		assertEquals( makeList( 2, 3, 4 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged(
				BiRefEdEntity.class, ed2_id,
				"referencing"
		);
		assertEquals( 1, list.size() );
		assertEquals( makeList( 4 ), extractRevisionNumbers( list ) );
	}
