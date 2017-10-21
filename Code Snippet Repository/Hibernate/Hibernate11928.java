	public void extent(String pckg) throws SQLException {
		if ( !isSupportedByDialect( SpatialFunction.extent ) ) {
			return;
		}
		// here we just check if we get a result, and can read it
		String hql = format(
				"SELECT id, extent(geom) from org.hibernate.spatial.integration.%s.GeomEntity group by id", pckg
		);
		Map<Integer, Object> hsreceived = new HashMap<Integer, Object>();
		doInSession( hql, hsreceived, new HashMap<String, Object>() );
	}
