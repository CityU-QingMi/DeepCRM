	@Test
	public void testEnumImplicitOverridedType() {
		MapValue mapValue = create();
		mapValue.enumImplicitOverrided = ImplicitEnumMapKey.VALUE_1;
		mapValue.mapEntity.enumImplicitOverridedType.put( mapValue.enumImplicitOverrided, mapValue );

		MapEntity found = persist( mapValue.mapEntity );

		assertEquals( 1, found.enumImplicitOverridedType.size() );
		MapValue foundValue = found.enumImplicitOverridedType.get( ImplicitEnumMapKey.VALUE_1 );
		assertEquals( ImplicitEnumMapKey.VALUE_1, foundValue.enumImplicitOverrided );

		assertEquals( "O1", findDatabaseValue( foundValue, "enumImplicitOverrided" ) );
		getSession().close();
	}
