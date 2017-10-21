	public void geometrytype(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.geometrytype ) ) {
			return;
		}
		Map<Integer, String> dbexpected = expectationsFactory.getGeometryType();
		String hql = format(
				"SELECT id, geometrytype(geom) from org.hibernate.spatial.integration.%s.GeomEntity",
				pckg
		);
		retrieveHQLResultsAndCompare( dbexpected, hql, pckg );
	}
