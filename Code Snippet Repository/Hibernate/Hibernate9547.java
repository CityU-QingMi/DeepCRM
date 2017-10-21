	@Override
	public void release() {
		if ( statement != null ) {
			try {
				statement.close();
			}
			catch (SQLException ignore) {
			}
		}
		if ( connection != null ) {
			try {

				connectionAccess.releaseConnection( connection );
			}
			catch (SQLException ignore) {
			}
		}
	}
