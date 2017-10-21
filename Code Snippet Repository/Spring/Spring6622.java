	@Test
	public void testStoredProcedureWithResultSet() throws Exception {
		ResultSet resultSet = mock(ResultSet.class);
		given(resultSet.next()).willReturn(true, true, false);
		given(callableStatement.execute()).willReturn(true);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(callableStatement.getResultSet()).willReturn(resultSet);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(connection.prepareCall("{call " + StoredProcedureWithResultSet.SQL + "()}")
				).willReturn(callableStatement);
		StoredProcedureWithResultSet sproc = new StoredProcedureWithResultSet(dataSource);
		sproc.execute();
		assertEquals(2, sproc.getCount());
		verify(resultSet).close();
	}
