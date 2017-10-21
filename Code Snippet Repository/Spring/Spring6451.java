	@Test
	public void testQueryWithResultSetExtractor() throws SQLException {
		given(resultSet.next()).willReturn(true);
		given(resultSet.getInt("id")).willReturn(1);
		given(resultSet.getString("forename")).willReturn("rod");

		params.put("id", new SqlParameterValue(Types.DECIMAL, 1));
		params.put("country", "UK");
		Customer cust = namedParameterTemplate.query(SELECT_NAMED_PARAMETERS, params,
				new ResultSetExtractor<Customer>() {
					@Override
					public Customer extractData(ResultSet rs) throws SQLException,
							DataAccessException {
						rs.next();
						Customer cust = new Customer();
						cust.setId(rs.getInt(COLUMN_NAMES[0]));
						cust.setForename(rs.getString(COLUMN_NAMES[1]));
						return cust;
					}
				});

		assertTrue("Customer id was assigned correctly", cust.getId() == 1);
		assertTrue("Customer forename was assigned correctly", cust.getForename().equals("rod"));
		verify(connection).prepareStatement(SELECT_NAMED_PARAMETERS_PARSED);
		verify(preparedStatement).setObject(1, 1, Types.DECIMAL);
		verify(preparedStatement).setString(2, "UK");
		verify(preparedStatement).close();
		verify(connection).close();
	}
