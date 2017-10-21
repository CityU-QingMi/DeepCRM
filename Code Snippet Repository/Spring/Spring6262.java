	@Override
	public Connection getConnection() throws SQLException {
		synchronized (this.connectionMonitor) {
			if (this.connection == null) {
				// No underlying Connection -> lazy init via DriverManager.
				initConnection();
			}
			if (this.connection.isClosed()) {
				throw new SQLException(
						"Connection was closed in SingleConnectionDataSource. Check that user code checks " +
						"shouldClose() before closing Connections, or set 'suppressClose' to 'true'");
			}
			return this.connection;
		}
	}
