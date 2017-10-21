    public void testInvalidPeriodDateSpecification() {
    	PreparedStatement stmt = null;
    	List<String> predicates = new LinkedList<String>();
    	Collections.addAll(predicates, "OVERLAPS", "EQUALS", "CONTAINS", "PRECEDES", "SUCCEEDS", "IMMEDIATELY PRECEDES", "IMMEDIATELY SUCCEEDS");
			for (String predicate: predicates) {
		    	String query = String.format("SELECT emp_id FROM PUBLIC.EMP WHERE PERIOD (BUS_START, BUS_END) %s PERIOD (?, ?);", predicate);
				try {
					stmt = conn.prepareStatement(query);
					// testing with start after end
					executeAndTestQuery(stmt, "TIMESTAMP '2000-01-11 01:02:03'", "TIMESTAMP '2000-01-10 01:02:03'");
					Assert.fail(String.format("An exception should have been raised for predicate %s when start is after end!", predicate));
				} catch (SQLDataException e) {
					// This is Ok. The test pass.
					Assert.assertEquals("data exception: invalid period value", e.getMessage());
				} catch (SQLException e) {
					e.printStackTrace();
					Assert.fail(e.getMessage());
				}
				try {
				// testing with start equals end
				executeAndTestQuery(stmt, "TIMESTAMP '2000-01-11 01:02:03'", "TIMESTAMP '2000-01-11 01:02:03'");
				Assert.fail(String.format("An exception should have been raised for predicate %s when start equals end!", predicate));
				} catch (SQLDataException e) {
					// This is Ok. The test pass.
					Assert.assertEquals("data exception: invalid period value", e.getMessage());
				} catch (SQLException e) {
					e.printStackTrace();
					Assert.fail(e.getMessage());
				}
			}
    }
