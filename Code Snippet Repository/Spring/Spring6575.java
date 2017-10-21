	private void doTestCustomerQuery(SqlQuery<?> query, boolean namedParameters) throws SQLException {
		given(resultSet.next()).willReturn(true);
		given(resultSet.getInt("id")).willReturn(1);
		given(resultSet.getString("forename")).willReturn("rod");
		given(resultSet.next()).willReturn(true, false);
		given(preparedStatement.executeQuery()).willReturn(resultSet);
		given(connection.prepareStatement(SELECT_ID_FORENAME_NAMED_PARAMETERS_PARSED)).willReturn(preparedStatement);

		List<?> queryResults;
		if (namedParameters) {
			Map<String, Object> params = new HashMap<>(2);
			params.put("id", 1);
			params.put("country", "UK");
			queryResults = query.executeByNamedParam(params);
		}
		else {
			Object[] params = new Object[] {1, "UK"};
			queryResults = query.execute(params);
		}
		assertTrue("Customer was returned correctly", queryResults.size() == 1);
		Customer cust = (Customer) queryResults.get(0);
		assertTrue("Customer id was assigned correctly", cust.getId() == 1);
		assertTrue("Customer forename was assigned correctly", cust.getForename().equals("rod"));

		verify(resultSet).close();
		verify(preparedStatement).setObject(1, 1, Types.INTEGER);
		verify(preparedStatement).setString(2, "UK");
		verify(preparedStatement).close();
	}
