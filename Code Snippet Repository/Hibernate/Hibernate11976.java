	@Override
	protected NativeSQLStatement createNativeDwithinStatement(
			Point geom,
			double distance) {
		String sql = "select t.id, ST_DWithin(t.geom, ST_GeomFromText(?, 4326), "
				+ distance
				+ " ) from GEOMTEST t where st_dwithin(t.geom, ST_GeomFromText(?, 4326), "
				+ distance + ") = 'true' and ST_SRID(t.geom) = 4326";
		return createNativeSQLStatementAllWKTParams( sql, geom.toText() );
	}
