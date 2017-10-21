	@Test
	public void testHasChangedEle() {
		List list = queryForPropertyHasChanged( EmbeddableListEntity1.class, ele1_id, "componentList" );
		assertEquals( 4, list.size() );
		assertEquals( makeList( 1, 3, 4, 5 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( EmbeddableListEntity1.class, ele1_id, "otherData" );
		assertEquals( 3, list.size() );
		assertEquals( makeList( 1, 2, 6 ), extractRevisionNumbers( list ) );
	}
