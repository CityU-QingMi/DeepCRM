	@Test
	public void testOwnerHasChanged() throws Exception {
		List list = queryForPropertyHasChanged(
				DoubleListJoinColumnBidirectionalRefEdEntity1.class, ed1_1_id,
				"owner"
		);
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 4 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged(
				DoubleListJoinColumnBidirectionalRefEdEntity1.class, ed1_1_id,
				"owner"
		);
		assertEquals( 1, list.size() );
		assertEquals( makeList( 3 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged(
				DoubleListJoinColumnBidirectionalRefEdEntity1.class, ed1_2_id,
				"owner"
		);
		assertEquals( 3, list.size() );
		assertEquals( makeList( 1, 2, 4 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged(
				DoubleListJoinColumnBidirectionalRefEdEntity1.class, ed1_2_id,
				"owner"
		);
		assertEquals( 0, list.size() );
	}
