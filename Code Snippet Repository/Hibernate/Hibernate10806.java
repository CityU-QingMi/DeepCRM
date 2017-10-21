	@Test
	public void testHasChanged() throws Exception {
		List list = queryForPropertyHasChangedWithDeleted(
				BasicTestEntity1.class,
				id1, "str1"
		);
		assertEquals( 2, list.size() );
		assertEquals( makeList( 1, 3 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChangedWithDeleted(
				BasicTestEntity1.class,
				id1, "long1"
		);
		assertEquals( 1, list.size() );
		assertEquals( makeList( 1 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChangedWithDeleted(
				BasicTestEntity1.class,
				id2, "str1"
		);
		// str1 property was null before insert and after insert so in a way it didn't change - is it a good way to go?
		assertEquals( 1, list.size() );
		assertEquals( makeList( 4 ), extractRevisionNumbers( list ) );

		list = queryForPropertyHasChangedWithDeleted(
				BasicTestEntity1.class,
				id2, "long1"
		);
		assertEquals( 1, list.size() );
		assertEquals( makeList( 2 ), extractRevisionNumbers( list ) );

		list = getAuditReader().createQuery().forRevisionsOfEntity( BasicTestEntity1.class, false, true )
				.add( AuditEntity.property( "str1" ).hasChanged() )
				.add( AuditEntity.property( "long1" ).hasChanged() )
				.getResultList();
		assertEquals( 1, list.size() );
		assertEquals( makeList( 1 ), extractRevisionNumbers( list ) );
	}
