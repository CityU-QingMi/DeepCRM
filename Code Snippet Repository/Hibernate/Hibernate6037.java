	public static void deleteAllUsers() throws SQLException {
		// afaict the only way to return update counts here is to actually perform some DML
		Connection conn = DriverManager.getConnection( "jdbc:default:connection" );
		System.out.println( "Preparing delete all" );
		PreparedStatement ps = conn.prepareStatement( "delete from t_user" );
		System.out.println( "Executing delete all" );
		int count = ps.executeUpdate();
		System.out.println( "Count : " + count );
		System.out.println( "Closing resources" );
		ps.close();
		conn.close();
	}
