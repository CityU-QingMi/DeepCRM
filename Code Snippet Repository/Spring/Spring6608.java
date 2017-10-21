	@Test
	@SuppressWarnings("")
	public void testStoredProcedureWithUndeclaredResults() throws Exception {
		ResultSet resultSet1 = mock(ResultSet.class);
		given(resultSet1.next()).willReturn(true, true, false);
		given(resultSet1.getString(2)).willReturn("Foo", "Bar");

		ResultSetMetaData resultSetMetaData = mock(ResultSetMetaData.class);
		given(resultSetMetaData.getColumnCount()).willReturn(2);
		given(resultSetMetaData.getColumnLabel(1)).willReturn("spam");
		given(resultSetMetaData.getColumnLabel(2)).willReturn("eggs");

		ResultSet resultSet2 = mock(ResultSet.class);
		given(resultSet2.getMetaData()).willReturn(resultSetMetaData);
		given(resultSet2.next()).willReturn(true, false);
		given(resultSet2.getObject(1)).willReturn("Spam");
		given(resultSet2.getObject(2)).willReturn("Eggs");

		given(callableStatement.execute()).willReturn(true);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(callableStatement.getResultSet()).willReturn(resultSet1, resultSet2);
		given(callableStatement.getMoreResults()).willReturn(true, false, false);
		given(callableStatement.getUpdateCount()).willReturn(-1, -1, 0, -1);
		given(connection.prepareCall("{call " + StoredProcedureWithResultSetMapped.SQL + "()}")
				).willReturn(callableStatement);

		StoredProcedureWithResultSetMapped sproc = new StoredProcedureWithResultSetMapped(dataSource);
		Map<String, Object> res = sproc.execute();

		assertEquals("incorrect number of returns", 3, res.size());

		List<String> rs1 = (List<String>) res.get("rs");
		assertEquals(2, rs1.size());
		assertEquals("Foo", rs1.get(0));
		assertEquals("Bar", rs1.get(1));

		List<Object> rs2 = (List<Object>) res.get("#result-set-2");
		assertEquals(1, rs2.size());
		Object o2 = rs2.get(0);
		assertTrue("wron type returned for result set 2", o2 instanceof Map);
		Map<String, String> m2 = (Map<String, String>) o2;
		assertEquals("Spam", m2.get("spam"));
		assertEquals("Eggs", m2.get("eggs"));

		Number n = (Number) res.get("#update-count-1");
		assertEquals("wrong update count", 0, n.intValue());
		verify(resultSet1).close();
		verify(resultSet2).close();
	}
