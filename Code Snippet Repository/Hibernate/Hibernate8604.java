	protected boolean isSerializableIsolationEnforced() throws Exception {
		JdbcConnectionAccess connectionAccess = sessionFactory().getServiceRegistry().getService( JdbcServices.class ).getBootstrapJdbcConnectionAccess();
		Connection conn = null;
		try {
			conn = connectionAccess.obtainConnection();
			return conn.getTransactionIsolation() >= Connection.TRANSACTION_SERIALIZABLE;
		}
		finally {
			if ( conn != null ) {
				try {
					connectionAccess.releaseConnection( conn );
				}
				catch ( Throwable ignore ) {
					// ignore...
				}
			}
		}
	}
