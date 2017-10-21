	@Test
	public void testMatchParameterValuesAndSqlInOutParameters() throws Exception {
		final String TABLE = "customers";
		final String USER = "me";
		given(databaseMetaData.getDatabaseProductName()).willReturn("MyDB");
		given(databaseMetaData.getUserName()).willReturn(USER);
		given(databaseMetaData.storesLowerCaseIdentifiers()).willReturn(true);

		List<SqlParameter> parameters = new ArrayList<>();
		parameters.add(new SqlParameter("id", Types.NUMERIC));
		parameters.add(new SqlInOutParameter("name", Types.NUMERIC));
		parameters.add(new SqlOutParameter("customer_no", Types.NUMERIC));

		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("id", 1);
		parameterSource.addValue("name", "Sven");
		parameterSource.addValue("customer_no", "12345XYZ");

		context.setProcedureName(TABLE);
		context.initializeMetaData(dataSource);
		context.processParameters(parameters);

		Map<String, Object> inParameters = context.matchInParameterValuesWithCallParameters(parameterSource);
		assertEquals("Wrong number of matched in parameter values", 2, inParameters.size());
		assertTrue("in parameter value missing", inParameters.containsKey("id"));
		assertTrue("in out parameter value missing", inParameters.containsKey("name"));
		assertTrue("out parameter value matched", !inParameters.containsKey("customer_no"));

		List<String> names = context.getOutParameterNames();
		assertEquals("Wrong number of out parameters", 2, names.size());

		List<SqlParameter> callParameters = context.getCallParameters();
		assertEquals("Wrong number of call parameters", 3, callParameters.size());
	}
