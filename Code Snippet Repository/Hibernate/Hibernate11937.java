	@Test
	public void dwithin() throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.dwithin ) ) {
			return;
		}
		Map<Integer, Boolean> dbexpected = expectationsFactory.getDwithin( expectationsFactory.getTestPoint(), 30.0 );
		Criterion spatialCriterion = SpatialRestrictions.distanceWithin(
				"geom",
				expectationsFactory.getTestPoint(),
				30.0
		);
		retrieveAndCompare( dbexpected, spatialCriterion );
	}
