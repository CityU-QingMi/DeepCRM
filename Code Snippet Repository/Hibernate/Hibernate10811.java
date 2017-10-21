	@Test
	public void testHasChanged() throws Exception {
		List list = queryForPropertyHasChanged(
				StringSetEntity.class, sse1_id,
				"strings"
		);
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 2 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged(
				StringSetEntity.class, sse2_id,
				"strings"
		);
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 3 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged(
				StringSetEntity.class, sse1_id,
				"strings"
		);
		assertEquals( 0, list.size() );

		list = queryForPropertyHasNotChanged(
				StringSetEntity.class, sse2_id,
				"strings"
		);
		assertEquals( 0, list.size() );
	}
