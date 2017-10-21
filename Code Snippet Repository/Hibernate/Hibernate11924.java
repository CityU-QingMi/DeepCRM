	public void geomunion(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.geomunion ) ) {
			return;
		}
		Map<Integer, Geometry> dbexpected = expectationsFactory.getGeomUnion( expectationsFactory.getTestPolygon() );
		String hql = format(
				"SELECT id, geomunion(geom, :polygon) from org.hibernate.spatial.integration.%s.GeomEntity " +
						"where srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "polygon", expectationsFactory.getTestPolygon() );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );
	}
