    public void testFirstPeriodEqualsSecondPeriod() throws SQLException {
    	String query = "SELECT emp_id FROM PUBLIC.EMP WHERE PERIOD (BUS_START, BUS_END) EQUALS PERIOD (?, ?);";
		PreparedStatement stmt = conn.prepareStatement(query);
    	ResultSet rs;
    	
    	executeAndTestQuery(stmt, "TIMESTAMP '2000-02-01 01:02:03'", "TIMESTAMP '2000-03-01 01:02:03'", 2);
    	stmt.close();
    	
    	query = "SELECT emp_id FROM PUBLIC.EMP WHERE PERIOD (?, ?) EQUALS PERIOD (BUS_START, BUS_END);";
    	stmt = conn.prepareStatement(query);

    	executeAndTestQuery(stmt, "TIMESTAMP '2000-02-01 01:02:03'", "TIMESTAMP '2000-03-01 01:02:03'", 2);
    	stmt.close();
    	
    	query = "SELECT emp_id FROM PUBLIC.EMP WHERE PERIOD (BUS_START, BUS_END) EQUALS PERIOD (TIMESTAMP '2000-03-01 01:02:03', INTERVAL '31' DAY);";
    	stmt = conn.prepareStatement(query);
    	rs = stmt.executeQuery();
    	assertAllIdsPresent(rs,3);
    	rs.close();
    	
    	stmt.close();
    }
