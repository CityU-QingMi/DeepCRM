	@Test
	public void testReferences2HasChanged() throws Exception {
		List list = queryForPropertyHasChanged(
				DoubleListJoinColumnBidirectionalRefIngEntity.class, ing1_id,
				"references2"
		);
		assertEquals( 3, list.size() );
		assertEquals( makeList( 1, 2, 4 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged(
				DoubleListJoinColumnBidirectionalRefIngEntity.class, ing2_id,
				"references2"
		);
		assertEquals( 3, list.size() );
		assertEquals( makeList( 1, 2, 4 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged(
				DoubleListJoinColumnBidirectionalRefIngEntity.class, ing1_id,
				"references2"
		);
		assertEquals( 0, list.size() );

		list = queryForPropertyHasNotChanged(
				DoubleListJoinColumnBidirectionalRefIngEntity.class, ing2_id,
				"references2"
		);
		assertEquals( 0, list.size() );
	}
