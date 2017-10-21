	@Test
	public void testHasChangedId1() throws Exception {
		List list =
				queryForPropertyHasChanged(
						OneToManyComponentTestEntity.class,
						otmcte_id1, "comp1"
				);
		assertEquals( 2, list.size() );
		assertEquals( makeList( 2, 3 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasNotChanged(
				OneToManyComponentTestEntity.class,
				otmcte_id1, "comp1"
		);
		assertEquals( 0, list.size() );
	}
