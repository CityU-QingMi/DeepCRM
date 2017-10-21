	public void within(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.within ) ) {
			return;
		}
		Map<Integer, Boolean> dbexpected = expectationsFactory.getWithin( expectationsFactory.getTestPolygon() );
		String hql = format(
				"SELECT id, within(geom, :filter) from org.hibernate.spatial.integration.%s.GeomEntity " +
						"where within(geom, :filter) = true and srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "filter", expectationsFactory.getTestPolygon() );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );
	}
