	public void difference(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.difference ) ) {
			return;
		}
		Map<Integer, Geometry> dbexpected = expectationsFactory.getDifference( expectationsFactory.getTestPolygon() );
		String hql = format(
				"SELECT id, difference(geom, :polygon) from org.hibernate.spatial.integration.%s.GeomEntity " +
						"where srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "polygon", expectationsFactory.getTestPolygon() );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );
	}
