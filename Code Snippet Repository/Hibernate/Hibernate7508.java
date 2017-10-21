	@Test
	public void testExplicitType() {
		MapValue mapValue = create();
		mapValue.explicitType = ColorType.RED;
		mapValue.mapEntity.explicitType.put( mapValue.explicitType, mapValue );

		MapEntity found = persist( mapValue.mapEntity );

		assertEquals( 1, found.explicitType.size() );
		MapValue foundValue = found.explicitType.get( ColorType.RED );
		assertEquals( ColorType.RED, foundValue.explicitType );

		assertEquals( "COLOR-red", findDatabaseValue( foundValue, "explicitType" ) );
		getSession().close();
	}
