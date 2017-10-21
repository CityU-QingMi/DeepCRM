	public Map<Integer, Object> rawDbObjects(String type) {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		Connection cn = null;
		PreparedStatement pstmt = null;
		ResultSet results = null;
		try {
			cn = getDataSource().getConnection();
			pstmt = cn.prepareStatement( "select id, geom from geomtest where type = ? order by id" );
			pstmt.setString( 1, type );
			results = pstmt.executeQuery();
			while ( results.next() ) {
				Integer id = results.getInt( 1 );
				Object obj = results.getObject( 2 );
				map.put( id, obj );
			}

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if ( results != null ) {
					results.close();
				}
			}
			catch (SQLException e) {
				//nothing to do
			}
			try {
				if ( pstmt != null ) {
					pstmt.close();
				}
			}
			catch (SQLException e) {
				//nothing to do
			}
			try {
				if ( cn != null ) {
					cn.close();
				}
			}
			catch (SQLException e) {
				// nothing we can do.
			}
		}
		return map;

	}
