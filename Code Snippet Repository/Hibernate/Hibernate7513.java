	@Test
	public void testEnumImplicitType() {
		MapValue mapValue = create();
		mapValue.enumImplicit = ImplicitEnumMapKey.VALUE_2;
		mapValue.mapEntity.enumImplicitType.put( mapValue.enumImplicit, mapValue );

		MapEntity found = persist( mapValue.mapEntity );

		assertEquals( 1, found.enumImplicitType.size() );
		MapValue foundValue = found.enumImplicitType.get( ImplicitEnumMapKey.VALUE_2 );
		assertEquals( ImplicitEnumMapKey.VALUE_2, foundValue.enumImplicit );

		assertEquals( "I2", findDatabaseValue( foundValue, "enumImplicit" ) );
		getSession().close();
	}
