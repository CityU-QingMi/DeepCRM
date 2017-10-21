	public void transform(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.transform ) ) {
			return;
		}
		int epsg = 4324;
		Map<Integer, Geometry> dbexpected = expectationsFactory.getTransform( epsg );
		String hql = format(
				"SELECT id, transform(geom, :epsg) from org.hibernate.spatial.integration.%s.GeomEntity " +
						"where srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "epsg", Integer.valueOf( epsg ) );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );

	}
