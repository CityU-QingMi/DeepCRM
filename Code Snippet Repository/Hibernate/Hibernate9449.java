	public static void selectAllEmployments(ResultSet[] resultSets) throws SQLException {
		Connection conn = DriverManager.getConnection( "jdbc:default:connection" );

		PreparedStatement statement = conn.prepareStatement(
				"select EMPLOYEE, EMPLOYER, STARTDATE, ENDDATE," +
						" REGIONCODE, EMPID, 'VALUE', CURRENCY" +
						" FROM EMPLOYMENT"
		);
		resultSets[0] = statement.executeQuery();

		conn.close();
	}
