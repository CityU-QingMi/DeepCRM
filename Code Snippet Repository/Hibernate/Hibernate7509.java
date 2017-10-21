	@Test
	public void testEnumDefaultType() {
		MapValue mapValue = create();
		mapValue.enumDefault = EnumMapKey.VALUE_1;
		mapValue.mapEntity.enumDefaultType.put( mapValue.enumDefault, mapValue );

		MapEntity found = persist( mapValue.mapEntity );

		assertEquals( 1, found.enumDefaultType.size() );
		MapValue foundValue = found.enumDefaultType.get( EnumMapKey.VALUE_1 );
		assertEquals( EnumMapKey.VALUE_1, foundValue.enumDefault );

		assertEquals( 0, ((Number) findDatabaseValue( foundValue, "enumDefault" )).intValue() );
		getSession().close();
	}
