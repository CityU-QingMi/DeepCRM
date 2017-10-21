	@Test
	public void testEnumImplicitOverrideOrdinalType() {
		MapValue mapValue = create();
		mapValue.enumImplicitOverrideOrdinal = ImplicitEnumMapKey.VALUE_1;
		mapValue.mapEntity.enumImplicitOverrideOrdinalType.put( mapValue.enumImplicitOverrideOrdinal, mapValue );

		MapEntity found = persist( mapValue.mapEntity );

		assertEquals( 1, found.enumImplicitOverrideOrdinalType.size() );
		MapValue foundValue = found.enumImplicitOverrideOrdinalType.get( ImplicitEnumMapKey.VALUE_1 );
		assertEquals( ImplicitEnumMapKey.VALUE_1, foundValue.enumImplicitOverrideOrdinal );

		assertEquals( 0, ((Number) findDatabaseValue( foundValue, "enumImplicitOverrideOrdinal" )).intValue() );
		getSession().close();
	}
