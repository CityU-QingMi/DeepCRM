	@Test
	public void testEnumImplicitOverrideStringType() {
		MapValue mapValue = create();
		mapValue.enumImplicitOverrideString = ImplicitEnumMapKey.VALUE_2;
		mapValue.mapEntity.enumImplicitOverrideStringType.put( mapValue.enumImplicitOverrideString, mapValue );

		MapEntity found = persist( mapValue.mapEntity );

		assertEquals( 1, found.enumImplicitOverrideStringType.size() );
		MapValue foundValue = found.enumImplicitOverrideStringType.get( ImplicitEnumMapKey.VALUE_2 );
		assertEquals( ImplicitEnumMapKey.VALUE_2, foundValue.enumImplicitOverrideString );

		assertEquals( "VALUE_2", findDatabaseValue( foundValue, "enumImplicitOverrideString" ) );
		getSession().close();
	}
