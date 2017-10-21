	@Test
	@TestForIssue(jiraKey = "")
	public void testOneToManyUpdateParentInsertChild() {
		List list = queryForPropertyHasChanged( ListRefEdEntity.class, parent2Id, "data" );
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 3 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( ListRefEdEntity.class, parent2Id, "reffering" );
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 3 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( ListRefIngEntity.class, child2Id, "reference" );
		assertEquals( 1, list.size() );
		assertEquals( makeList( 3 ), extractRevisionNumbers( list ) );
	}
