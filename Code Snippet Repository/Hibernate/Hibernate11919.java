	public void buffer(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.buffer ) ) {
			return;
		}
		Map<Integer, Geometry> dbexpected = expectationsFactory.getBuffer( Double.valueOf( 1.0 ) );
		String hql = format(
				"SELECT id, buffer(geom, :distance) from org.hibernate.spatial.integration.%s.GeomEntity " +
						"where srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "distance", Double.valueOf( 1.0 ) );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );

	}
