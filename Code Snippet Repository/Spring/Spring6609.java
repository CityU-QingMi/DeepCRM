	@Test
	public void testStoredProcedureSkippingResultsProcessing() throws Exception {
		given(callableStatement.execute()).willReturn(true);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(connection.prepareCall("{call " + StoredProcedureWithResultSetMapped.SQL + "()}")
				).willReturn(callableStatement);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setSkipResultsProcessing(true);
		StoredProcedureWithResultSetMapped sproc = new StoredProcedureWithResultSetMapped(
				jdbcTemplate);
		Map<String, Object> res = sproc.execute();
		assertEquals("incorrect number of returns", 0, res.size());
	}
