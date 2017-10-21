	@Test
	public void testOwnerSecEntityHasChanged() throws Exception {
		List list = queryForPropertyHasChanged(
				DoubleListJoinColumnBidirectionalRefEdEntity2.class, ed2_1_id,
				"owner"
		);
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 4 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged(
				DoubleListJoinColumnBidirectionalRefEdEntity2.class, ed2_1_id,
				"owner"
		);
		assertEquals( 0, list.size() );

		list = queryForPropertyHasChanged(
				DoubleListJoinColumnBidirectionalRefEdEntity2.class, ed2_2_id,
				"owner"
		);
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 2 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged(
				DoubleListJoinColumnBidirectionalRefEdEntity2.class, ed2_2_id,
				"owner"
		);
		assertEquals( 1, list.size() );
		assertEquals( makeList( 3 ), extractRevisionNumbers( list ) );
	}
