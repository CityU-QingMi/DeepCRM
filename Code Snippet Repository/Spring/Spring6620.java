	@Test
	public void testStoredProcedureConfiguredViaJdbcTemplate() throws Exception {
		given(callableStatement.execute()).willReturn(false);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(callableStatement.getObject(2)).willReturn(4);
		given(connection.prepareCall("{call " + StoredProcedureConfiguredViaJdbcTemplate.SQL + "(?, ?)}")
				).willReturn(callableStatement);
		JdbcTemplate t = new JdbcTemplate();
		t.setDataSource(dataSource);
		StoredProcedureConfiguredViaJdbcTemplate sp = new StoredProcedureConfiguredViaJdbcTemplate(t);
		assertEquals(sp.execute(1106), 4);
		verify(callableStatement).setObject(1, 1106, Types.INTEGER);
		verify(callableStatement).registerOutParameter(2, Types.INTEGER);
	}
