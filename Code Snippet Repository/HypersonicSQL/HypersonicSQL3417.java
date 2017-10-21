    public void testPeriodOverlapsSinglePointInTime() throws SQLException {
    	
    	String query = "SELECT emp_id FROM PUBLIC.EMP WHERE (BUS_START, BUS_END) OVERLAPS (?, ?);";
		PreparedStatement stmt = conn.prepareStatement(query);
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '2000-01-11 01:02:03'", "TIMESTAMP '2000-01-11 01:02:03'", 1);
    	
    	stmt.close();

       	query = "SELECT emp_id FROM PUBLIC.EMP WHERE (?, ?) OVERLAPS (BUS_START, BUS_END);";
       	stmt = conn.prepareStatement(query);
    	executeAndTestQuery(stmt, "TIMESTAMP '2000-01-11 01:02:03'", "TIMESTAMP '2000-01-11 01:02:03'", 1);

       	stmt.close();
    }
