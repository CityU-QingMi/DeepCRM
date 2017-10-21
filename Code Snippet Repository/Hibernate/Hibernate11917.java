	public void relate(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.relate ) ) {
			return;
		}
		String matrix = "T*T***T**";
		Map<Integer, Boolean> dbexpected = expectationsFactory.getRelate(
				expectationsFactory.getTestPolygon(),
				matrix
		);
		String hql = format(
				"SELECT id, relate(geom, :filter, :matrix) from org.hibernate.spatial.integration.%s.GeomEntity " +
						"where relate(geom, :filter, :matrix) = true and srid(geom) = 4326", pckg
		);
		Map<String, Object> params = createQueryParams( "filter", expectationsFactory.getTestPolygon() );
		params.put( "matrix", matrix );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );

		matrix = "FF*FF****";
		dbexpected = expectationsFactory.getRelate( expectationsFactory.getTestPolygon(), matrix );
		params.put( "matrix", matrix );
		retrieveHQLResultsAndCompare( dbexpected, hql, params, pckg );

	}
