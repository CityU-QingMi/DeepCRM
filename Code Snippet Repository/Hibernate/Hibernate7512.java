	@Test
	public void testEnumExplicitType() {
		MapValue mapValue = create();
		mapValue.enumExplicit = EnumMapKey.VALUE_2;
		mapValue.mapEntity.enumExplicitType.put( mapValue.enumExplicit, mapValue );

		MapEntity found = persist( mapValue.mapEntity );

		assertEquals( 1, found.enumExplicitType.size() );
		MapValue foundValue = found.enumExplicitType.get( EnumMapKey.VALUE_2 );
		assertEquals( EnumMapKey.VALUE_2, foundValue.enumExplicit );

		assertEquals( "2", findDatabaseValue( foundValue, "enumExplicit" ) );
		getSession().close();
	}
