	private void setGeomMetaDataTo2D() throws SQLException {
		String sql1 = "delete from user_sdo_geom_metadata where TABLE_NAME = 'GEOMTEST'";
		String sql2 = "insert into user_sdo_geom_metadata values (" +
				"  'GEOMTEST'," +
				"  'geom'," +
				"  SDO_DIM_ARRAY(" +
				"    SDO_DIM_ELEMENT('X', -180, 180, 0.00001)," +
				"    SDO_DIM_ELEMENT('Y', -90, 90, 0.00001)" +
				"    )," +
				"  4326)";
		executeStatement( sql1 );
		executeStatement( sql2 );

	}
