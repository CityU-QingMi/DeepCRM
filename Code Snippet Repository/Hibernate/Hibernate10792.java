	@Test
	public void testHasChanged() throws Exception {
		List list = queryForPropertyHasChanged(
				EnumSetEntity.class, sse1_id,
				"enums1"
		);
		assertEquals( 3, list.size() );
		assertEquals( makeList( 1, 2, 3 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged(
				EnumSetEntity.class, sse1_id,
				"enums2"
		);
		assertEquals( 1, list.size() );
		assertEquals( makeList( 1 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged(
				EnumSetEntity.class, sse1_id,
				"enums1"
		);
		assertEquals( 0, list.size() );

		list = queryForPropertyHasNotChanged(
				EnumSetEntity.class, sse1_id,
				"enums2"
		);
		assertEquals( 2, list.size() );
		assertEquals( makeList( 2, 3 ), extractRevisionNumbers( list ) );
	}
