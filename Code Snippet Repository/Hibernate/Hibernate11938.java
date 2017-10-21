	@Test
	public void havingSRID() throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.srid ) ) {
			return;
		}
		Map<Integer, Boolean> dbexpected = expectationsFactory.havingSRID( 4326 );
		Criterion spatialCriterion = SpatialRestrictions.havingSRID( "geom", 4326 );
		retrieveAndCompare( dbexpected, spatialCriterion );
		dbexpected = expectationsFactory.havingSRID( 31370 );
		spatialCriterion = SpatialRestrictions.havingSRID( "geom", 31370 );
		retrieveAndCompare( dbexpected, spatialCriterion );
	}
