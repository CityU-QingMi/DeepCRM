	public void touches(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.touches ) ) {
			return;
		}
		String hql = format(
				"SELECT id, touches(geom, :filter) from org.hibernate.spatial.integration.%s.GeomEntity " +
						"where touches(geom, :filter) = true and srid(geom) = 4326", pckg
		);
		Map<Integer, Boolean> dbexpected = expectationsFactory.getTouches( expectationsFactory.getTestPolygon() );
		Map<String, Object> params = createQueryParams( "filter", expectationsFactory.getTestPolygon() );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );
	}
