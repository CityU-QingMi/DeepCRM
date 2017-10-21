	@Test
	public void testHasChangedComponentEntity() throws Exception {
		List list = queryForPropertyHasChanged(
				ComponentTestEntity.class,
				cte1_id, "comp1"
		);
		assertEquals( 1, list.size() );
		assertEquals( makeList( 1 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged(
				ComponentTestEntity.class, cte1_id,
				"comp1"
		);
		assertEquals( 0, list.size() );

		list = queryForPropertyHasChanged( ComponentTestEntity.class, cte2_id, "comp1" );
		assertEquals( 1, list.size() );
		assertEquals( makeList( 1 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged( ComponentTestEntity.class, cte2_id, "comp1" );
		assertEquals( 0, list.size() );
	}
