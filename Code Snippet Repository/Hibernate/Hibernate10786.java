	@Test
	public void testHasChanged() {
		List list = queryForPropertyHasChanged( MultipleCollectionEntity.class, mce1Id, "text" );
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 4 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( MultipleCollectionEntity.class, mce1Id, "refEntities1" );
		assertEquals( 3, list.size() );
		assertEquals( makeList( 1, 2, 3 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( MultipleCollectionEntity.class, mce1Id, "refEntities2" );
		assertEquals( 1, list.size() );
		assertEquals( makeList( 1 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( MultipleCollectionRefEntity1.class, mcre1Id, "text" );
		assertEquals( 1, list.size() );
		assertEquals( makeList( 2 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( MultipleCollectionEntity.class, mce2Id, "text" );
		assertEquals( 1, list.size() );
		assertEquals( makeList( 5 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( MultipleCollectionEntity.class, mce2Id, "refEntities2" );
		assertEquals( 1, list.size() );
		assertEquals( makeList( 5 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChanged( MultipleCollectionRefEntity2.class, mcre2Id, "text" );
		assertEquals( 1, list.size() );
		assertEquals( makeList( 5 ), extractRevisionNumbers( list ) );
	}
