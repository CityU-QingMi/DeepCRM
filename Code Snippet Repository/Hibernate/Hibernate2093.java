	@Override
	public Connection createConnection() {
		final Connection conn = makeConnection( url, connectionProps );
		if ( conn == null ) {
			throw new HibernateException( "Unable to make JDBC Connection [" + url + "]" );
		}

		try {
			if ( isolation != null ) {
				conn.setTransactionIsolation( isolation );
			}
		}
		catch (SQLException e) {
			throw convertSqlException( "Unable to set transaction isolation (" + isolation + ")", e );
		}

		try {
			if ( conn.getAutoCommit() != autoCommit ) {
				conn.setAutoCommit( autoCommit );
			}
		}
		catch (SQLException e) {
			throw convertSqlException( "Unable to set auto-commit (" + autoCommit + ")", e );
		}

		return conn;
	}
