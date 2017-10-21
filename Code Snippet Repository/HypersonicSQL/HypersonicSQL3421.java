    public void testFirstPeriodOverlapsSecondPeriodReversed() throws SQLException {
    	
    	String query = "SELECT emp_id FROM PUBLIC.EMP WHERE PERIOD (?, ?) OVERLAPS PERIOD (BUS_START, BUS_END);";
		PreparedStatement stmt = conn.prepareStatement(query);

    	executeAndTestQuery(stmt, "TIMESTAMP '1999-12-01 01:02:03'", "TIMESTAMP '2000-01-12 01:02:03'", 1);
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '1999-12-01 01:02:03'", "TIMESTAMP '2000-01-01 01:02:03'");
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '1999-12-01 01:02:03'", "TIMESTAMP '2000-12-31 01:02:03'", 1, 2, 3);
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '2000-04-01 01:02:03'", "TIMESTAMP '2000-05-01 01:02:03'");
    	
    	stmt.close();
    }
