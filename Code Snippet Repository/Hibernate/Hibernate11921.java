	public void intersection(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.intersection ) ) {
			return;
		}
		Map<Integer, Geometry> dbexpected = expectationsFactory.getIntersection( expectationsFactory.getTestPolygon() );
		String hql = format(
				"SELECT id, intersection(geom, :polygon) from org.hibernate.spatial.integration.%s.GeomEntity " +
						"where srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "polygon", expectationsFactory.getTestPolygon() );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );
	}
