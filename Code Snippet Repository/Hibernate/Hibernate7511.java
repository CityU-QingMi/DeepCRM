	@Test
	public void testEnumExplicitStringType() {
		MapValue mapValue = create();
		mapValue.enumExplicitString = EnumMapKey.VALUE_1;
		mapValue.mapEntity.enumExplicitStringType.put( mapValue.enumExplicitString, mapValue );

		MapEntity found = persist( mapValue.mapEntity );

		assertEquals( 1, found.enumExplicitStringType.size() );
		MapValue foundValue = found.enumExplicitStringType.get( EnumMapKey.VALUE_1 );
		assertEquals( EnumMapKey.VALUE_1, foundValue.enumExplicitString );

		assertEquals( "VALUE_1", findDatabaseValue( foundValue, "enumExplicitString" ) );
		getSession().close();
	}
