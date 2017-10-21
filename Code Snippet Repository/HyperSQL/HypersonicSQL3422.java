    public void testFirstPeriodOverlapsWithInterval() throws SQLException {
    	String query = "SELECT emp_id FROM PUBLIC.EMP WHERE PERIOD (?, INTERVAL '40' DAY) OVERLAPS PERIOD (BUS_START, BUS_END);";
		PreparedStatement stmt = conn.prepareStatement(query);
    	ResultSet rs;
    	
    	stmt.setString(1, "TIMESTAMP '2000-02-01 01:02:03'");
    	rs = stmt.executeQuery();
    	assertAllIdsPresent(rs, 2, 3);
    	rs.close();
    	
    	stmt.setString(1, "TIMESTAMP '1999-02-01 01:02:03'");
    	rs = stmt.executeQuery();
    	assertAllIdsPresent(rs);
    	rs.close();
    	
    	stmt.close();
    }
