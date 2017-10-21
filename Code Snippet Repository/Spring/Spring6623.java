	@Test
	@SuppressWarnings("")
	public void testStoredProcedureWithResultSetMapped() throws Exception {
		ResultSet resultSet = mock(ResultSet.class);
		given(resultSet.next()).willReturn(true, true, false);
		given(resultSet.getString(2)).willReturn("Foo", "Bar");
		given(callableStatement.execute()).willReturn(true);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(callableStatement.getResultSet()).willReturn(resultSet);
		given(callableStatement.getMoreResults()).willReturn(false);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(connection.prepareCall("{call " + StoredProcedureWithResultSetMapped.SQL + "()}")
				).willReturn(callableStatement);
		StoredProcedureWithResultSetMapped sproc = new StoredProcedureWithResultSetMapped(dataSource);
		Map<String, Object> res = sproc.execute();
		List<String> rs = (List<String>) res.get("rs");
		assertEquals(2, rs.size());
		assertEquals("Foo", rs.get(0));
		assertEquals("Bar", rs.get(1));
		verify(resultSet).close();
	}
