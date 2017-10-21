	@Test
	public void testHasChanged() throws Exception {
		List list = queryForPropertyHasChanged(
				IdMapKeyEntity.class, imke_id,
				"idmap"
		);
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 2 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged(
				IdMapKeyEntity.class, imke_id,
				"idmap"
		);
		assertEquals( 0, list.size() );
	}
