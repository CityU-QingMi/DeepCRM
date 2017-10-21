	public static void simpleScalar(short i, ResultSet[] resultSets) throws SQLException {
		Connection conn = DriverManager.getConnection( "jdbc:default:connection" );

		PreparedStatement statement = conn.prepareStatement(
				"SELECT " + i + " as value, 'getAll' as name from sysibm.sysdummy1"
		);
		resultSets[0] = statement.executeQuery();

		conn.close();
	}
