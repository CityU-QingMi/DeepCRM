	public void dwithin(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.dwithin ) ) {
			return;
		}
		double distance = 30.0;
		Map<Integer, Boolean> dbexpected = expectationsFactory.getDwithin(
				expectationsFactory.getTestPoint(),
				distance
		);
		String hql = format(
				"SELECT id, dwithin(geom, :filter, :distance) from " +
						"org.hibernate.spatial.integration.%s.GeomEntity where dwithin(geom, :filter, :distance) = true " +
						"and srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "filter", expectationsFactory.getTestPoint() );
		if ( getDialect() instanceof OracleSpatial10gDialect ) {
			//because this uses the weird syntax and conventions of SDO_WITHIN_DISTANCE which returns a string (really)
			// we use a different boolean expression guaranteed to be true, and we set the third parameter to key/value string
			hql = "SELECT id, issimple(geom) from org.hibernate.spatial.integration.GeomEntity where dwithin(geom, :filter, :distance) = true and srid(geom) = 4326";
			params.put( "distance", "distance = 30" );
		}
		else {
			params.put( "distance", 30.0 );
		}
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );
	}
