	public void intersects(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.intersects ) ) {
			return;
		}
		Map<Integer, Boolean> dbexpected = expectationsFactory.getIntersects( expectationsFactory.getTestPolygon() );
		String hql = format(
				"SELECT id, intersects(geom, :filter) from org.hibernate.spatial.integration.%s.GeomEntity " +
						"where intersects(geom, :filter) = true and srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "filter", expectationsFactory.getTestPolygon() );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );
	}
