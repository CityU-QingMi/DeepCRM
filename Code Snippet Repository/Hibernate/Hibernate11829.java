	@Override
	public Connection getConnection() throws SQLException {
		// get a connection from the pool (thru DriverManager, cfr. Proxool doc)
		final Connection c = DriverManager.getConnection( proxoolAlias );

		// set the Transaction Isolation if defined
		if ( isolation != null ) {
			c.setTransactionIsolation( isolation );
		}

		// toggle autoCommit to false if set
		if ( c.getAutoCommit() != autocommit ) {
			c.setAutoCommit( autocommit );
		}

		// return the connection
		return c;
	}
