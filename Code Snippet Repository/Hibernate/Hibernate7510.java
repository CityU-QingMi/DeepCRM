	@Test
	public void testEnumExplicitOrdinalType() {
		MapValue mapValue = create();
		mapValue.enumExplicitOrdinal = EnumMapKey.VALUE_2;
		mapValue.mapEntity.enumExplicitOrdinalType.put( mapValue.enumExplicitOrdinal, mapValue );

		MapEntity found = persist( mapValue.mapEntity );

		assertEquals( 1, found.enumExplicitOrdinalType.size() );
		MapValue foundValue = found.enumExplicitOrdinalType.get( EnumMapKey.VALUE_2 );
		assertEquals( EnumMapKey.VALUE_2, foundValue.enumExplicitOrdinal );

		assertEquals( 1, ((Number) findDatabaseValue( foundValue, "enumExplicitOrdinal" )).intValue() );
		getSession().close();
	}
