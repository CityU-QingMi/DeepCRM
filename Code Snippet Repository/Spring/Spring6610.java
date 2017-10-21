	@Test
	@SuppressWarnings("")
	public void testStoredProcedureSkippingUndeclaredResults() throws Exception {
		ResultSet resultSet = mock(ResultSet.class);
		given(resultSet.next()).willReturn(true, true, false);
		given(resultSet.getString(2)).willReturn("Foo", "Bar");
		given(callableStatement.execute()).willReturn(true);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(callableStatement.getResultSet()).willReturn(resultSet);
		given(callableStatement.getMoreResults()).willReturn(true, false);
		given(callableStatement.getUpdateCount()).willReturn(-1, -1);
		given(connection.prepareCall("{call " + StoredProcedureWithResultSetMapped.SQL + "()}")
				).willReturn(callableStatement);

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.setSkipUndeclaredResults(true);
		StoredProcedureWithResultSetMapped sproc = new StoredProcedureWithResultSetMapped(
				jdbcTemplate);
		Map<String, Object> res = sproc.execute();

		assertEquals("incorrect number of returns", 1, res.size());
		List<String> rs1 = (List<String>) res.get("rs");
		assertEquals(2, rs1.size());
		assertEquals("Foo", rs1.get(0));
		assertEquals("Bar", rs1.get(1));
		verify(resultSet).close();
	}
