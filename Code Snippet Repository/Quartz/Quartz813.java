	public static int triggersInAcquiredState() throws SQLException {
		int triggersInAcquiredState = 0;
		Connection conn = DriverManager.getConnection(DATABASE_CONNECTION_PREFIX, PROPS);
		try {
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery("SELECT count( * ) FROM QRTZ_TRIGGERS WHERE TRIGGER_STATE = 'ACQUIRED' ");
			while (result.next()) { 
				triggersInAcquiredState = result.getInt(1);
			}
		} finally {
			conn.close();
		}
		return triggersInAcquiredState;
	}
