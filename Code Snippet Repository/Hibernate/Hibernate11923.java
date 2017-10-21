	public void symdifference(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.symdifference ) ) {
			return;
		}
		Map<Integer, Geometry> dbexpected = expectationsFactory.getSymDifference( expectationsFactory.getTestPolygon() );
		String hql = format(
				"SELECT id, symdifference(geom, :polygon) from " +
						"org.hibernate.spatial.integration.%s.GeomEntity where srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "polygon", expectationsFactory.getTestPolygon() );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );
	}
