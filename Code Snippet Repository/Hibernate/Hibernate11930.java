	public void equals(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.equals ) ) {
			return;
		}
		Map<Integer, Boolean> dbexpected = expectationsFactory.getEquals( expectationsFactory.getTestPolygon() );
		String hql = format(
				"SELECT id, equals(geom, :filter) from org.hibernate.spatial.integration.%s.GeomEntity " +
						"where equals(geom, :filter) = true and srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "filter", expectationsFactory.getTestPolygon() );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );
	}
