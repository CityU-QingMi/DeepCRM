	@Override
	public void prepare() {
		try {
			connection = connectionAccess.obtainConnection();
			connection.setAutoCommit( true );
		}
		catch (SQLException e) {
			throw new SchemaManagementException( "Unable to open JDBC connection for schema management target", e );
		}

		try {
			statement = connection.createStatement();
		}
		catch (SQLException e) {
			throw new SchemaManagementException( "Unable to create JDBC Statement for schema management target", e );
		}
	}
