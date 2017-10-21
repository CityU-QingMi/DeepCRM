	public void convexhull(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.convexhull ) ) {
			return;
		}
		Map<Integer, Geometry> dbexpected = expectationsFactory.getConvexHull( expectationsFactory.getTestPolygon() );
		String hql = format(
				"SELECT id, convexhull(geomunion(geom, :polygon)) from org.hibernate.spatial.integration" +
						".%s.GeomEntity where srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "polygon", expectationsFactory.getTestPolygon() );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );

	}
