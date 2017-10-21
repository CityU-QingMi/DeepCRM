	@Test
	public void testStoredProcedureConfiguredViaJdbcTemplateWithCustomExceptionTranslator()
			throws Exception {
		given(callableStatement.execute()).willReturn(false);
		given(callableStatement.getUpdateCount()).willReturn(-1);
		given(callableStatement.getObject(2)).willReturn(5);
		given(connection.prepareCall("{call " + StoredProcedureConfiguredViaJdbcTemplate.SQL + "(?, ?)}")
				).willReturn(callableStatement);

		class TestJdbcTemplate extends JdbcTemplate {

			int calls;

			@Override
			public Map<String, Object> call(CallableStatementCreator csc,
					List<SqlParameter> declaredParameters) throws DataAccessException {
				calls++;
				return super.call(csc, declaredParameters);
			}
		}
		TestJdbcTemplate t = new TestJdbcTemplate();
		t.setDataSource(dataSource);
		// Will fail without the following, because we're not able to get a connection
		// from the DataSource here if we need to create an ExceptionTranslator
		t.setExceptionTranslator(new SQLStateSQLExceptionTranslator());
		StoredProcedureConfiguredViaJdbcTemplate sp = new StoredProcedureConfiguredViaJdbcTemplate(t);

		assertEquals(sp.execute(11), 5);
		assertEquals(1, t.calls);

		verify(callableStatement).setObject(1, 11, Types.INTEGER);
		verify(callableStatement).registerOutParameter(2, Types.INTEGER);
	}
