	@Test
	public void testImplicitType() {
		MapValue mapValue = create();
		mapValue.implicitType = ColorType.BLUE;
		mapValue.mapEntity.implicitType.put( mapValue.implicitType, mapValue );

		MapEntity found = persist( mapValue.mapEntity );

		assertEquals( 1, found.implicitType.size() );
		MapValue foundValue = found.implicitType.get( ColorType.BLUE );
		assertEquals( ColorType.BLUE, foundValue.implicitType );

		assertEquals( "blue", findDatabaseValue( foundValue, "implicitType" ) );
		getSession().close();
	}
