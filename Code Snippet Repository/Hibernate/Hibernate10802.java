	@Test
	@TestForIssue(jiraKey = "")
	public void testOneToManyInsertChildUpdateParent() {
		List list = queryForPropertyHasChanged( ListRefEdEntity.class, parent1Id, "data" );
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 2 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( ListRefEdEntity.class, parent1Id, "reffering" );
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 2 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( ListRefIngEntity.class, child1Id, "reference" );
		assertEquals( 1, list.size() );
		assertEquals( makeList( 2 ), extractRevisionNumbers( list ) );
	}
