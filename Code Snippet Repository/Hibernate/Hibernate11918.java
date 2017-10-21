	public void distance(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.distance ) ) {
			return;
		}
		Map<Integer, Double> dbexpected = expectationsFactory.getDistance( expectationsFactory.getTestPolygon() );
		String hql = format(
				"SELECT id, distance(geom, :filter) from org.hibernate.spatial.integration.%s.GeomEntity " +
						"where srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "filter", expectationsFactory.getTestPolygon() );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );
	}
