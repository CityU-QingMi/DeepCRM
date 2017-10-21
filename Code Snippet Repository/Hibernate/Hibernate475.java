	@Override
	@SuppressWarnings("")
	public Connection getConnection() throws SQLException {
		final Connection c = ds.getConnection();
		if ( isolation != null ) {
			c.setTransactionIsolation( isolation.intValue() );
		}
		if ( c.getAutoCommit() != autocommit ) {
			c.setAutoCommit( autocommit );
		}
		return c;
	}
