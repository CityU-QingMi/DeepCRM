	public static BigDecimal timesTriggered(String triggerName,String triggerGroup) throws SQLException {
		BigDecimal timesTriggered = BigDecimal.ZERO;
		Connection conn = DriverManager.getConnection(DATABASE_CONNECTION_PREFIX, PROPS);
		try {
			PreparedStatement ps = conn.prepareStatement("SELECT TIMES_TRIGGERED FROM QRTZ_SIMPLE_TRIGGERS WHERE TRIGGER_NAME = ? AND TRIGGER_GROUP = ? ");
			ps.setString(1, triggerName);
			ps.setString(2, triggerGroup);
			ResultSet result = ps.executeQuery();
			result.next(); 
			timesTriggered = result.getBigDecimal(1);
		} finally {
			conn.close();
		}
		return timesTriggered;
	}
