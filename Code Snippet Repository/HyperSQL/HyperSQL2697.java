    public void testFirstPeriodContainsDate() throws SQLException {
    	String query = "SELECT emp_id FROM PUBLIC.EMP WHERE PERIOD (BUS_START, BUS_END) CONTAINS TIMESTAMP '2000-02-01 01:02:03';";
		PreparedStatement stmt = conn.prepareStatement(query);
    	ResultSet rs;
    	
    	rs = stmt.executeQuery();
    	assertAllIdsPresent(rs, 2);
    	rs.close();
    	stmt.close();
    	
    	query = "SELECT emp_id FROM PUBLIC.EMP WHERE PERIOD (BUS_START, BUS_END) CONTAINS TIMESTAMP '1999-02-01 01:02:03';";
    	stmt = conn.prepareStatement(query);
    	rs = stmt.executeQuery();
    	assertAllIdsPresent(rs);
    	rs.close();

    	stmt.close();
    }
