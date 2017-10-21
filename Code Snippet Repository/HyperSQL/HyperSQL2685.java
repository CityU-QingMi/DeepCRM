    public void testFirstPeriodSuccedesSecondPeriod() throws SQLException {
    	String query = "SELECT emp_id FROM PUBLIC.EMP WHERE PERIOD (BUS_START, BUS_END) SUCCEEDS PERIOD (?, ?);";
		PreparedStatement stmt = conn.prepareStatement(query);
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '2000-04-01 01:02:03'", "TIMESTAMP '2000-05-01 01:02:03'");
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '1999-03-01 01:02:03'", "TIMESTAMP '1999-03-30 01:02:03'", 1, 2, 3);
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '1999-12-01 01:02:03'", "TIMESTAMP '2000-01-12 01:02:03'", 2, 3);
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '2000-01-01 01:02:03'", "TIMESTAMP '2000-03-01 01:02:03'", 3);
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '2000-02-01 01:02:03'", "TIMESTAMP '2000-03-01 01:02:03'", 3);
    	
    	stmt.close();
    }
