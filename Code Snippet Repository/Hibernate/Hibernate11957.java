	public void deleteTestData() throws SQLException {
		Connection cn = null;
		try {
			cn = getDataSource().getConnection();
			cn.setAutoCommit( false );
			PreparedStatement pmt = cn.prepareStatement( "delete from GEOMTEST" );
			if ( !pmt.execute() ) {
				int updateCount = pmt.getUpdateCount();
				LOG.info( "Removing " + updateCount + " rows." );
			}
			cn.commit();
			pmt.close();
		}
		finally {
			try {
				if ( cn != null ) {
					cn.close();
				}
			}
			catch (SQLException e) {
				// nothing to do
			}
		}
	}
