    public void testFirstPeriodContainsSecondPeriod() throws SQLException {
    	String query = "SELECT emp_id FROM PUBLIC.EMP WHERE PERIOD (BUS_START, BUS_END) CONTAINS PERIOD (?, ?);";
		PreparedStatement stmt = conn.prepareStatement(query);
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '2000-02-01 01:02:03'", "TIMESTAMP '2000-02-01 01:02:04'", 2);
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '1999-03-01 01:02:03'", "TIMESTAMP '1999-04-01 01:02:03'");
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '2000-03-01 01:02:03'", "TIMESTAMP '2000-03-30 01:02:03'", 3);
    	
    	stmt.close();
    	
    	query = "SELECT emp_id FROM PUBLIC.EMP WHERE PERIOD (?, ?) CONTAINS PERIOD (BUS_START, BUS_END);";
    	stmt = conn.prepareStatement(query);

    	executeAndTestQuery(stmt, "TIMESTAMP '1999-03-01 01:02:03'", "TIMESTAMP '2001-03-01 01:02:03'", 1, 2, 3);
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '2000-01-01 01:02:03'", "TIMESTAMP '2001-04-01 01:02:03'", 1, 2, 3);
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '2000-01-31 01:02:03'", "TIMESTAMP '2000-03-01 01:02:03'", 2);
    	
    	stmt.close();
    }
