	@Override
	public void afterCreateSchema() {
		try {
			executeStatement("ALTER TABLE GEOMTEST DROP (GEOM)");
			executeStatement("ALTER TABLE GEOMTEST ADD (GEOM ST_GEOMETRY(4326))");
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
