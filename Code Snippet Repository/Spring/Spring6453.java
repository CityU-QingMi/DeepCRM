	@Test
	public void testQueryWithRowCallbackHandler() throws SQLException {
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt("id")).willReturn(1);
		given(resultSet.getString("forename")).willReturn("rod");

		params.put("id", new SqlParameterValue(Types.DECIMAL, 1));
		params.put("country", "UK");
		final List<Customer> customers = new LinkedList<>();
		namedParameterTemplate.query(SELECT_NAMED_PARAMETERS, params, new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Customer cust = new Customer();
				cust.setId(rs.getInt(COLUMN_NAMES[0]));
				cust.setForename(rs.getString(COLUMN_NAMES[1]));
				customers.add(cust);
			}
		});

		assertEquals(1, customers.size());
		assertTrue("Customer id was assigned correctly", customers.get(0).getId() == 1);
		assertTrue("Customer forename was assigned correctly", customers.get(0).getForename().equals("rod"));
		verify(connection).prepareStatement(SELECT_NAMED_PARAMETERS_PARSED);
		verify(preparedStatement).setObject(1, 1, Types.DECIMAL);
		verify(preparedStatement).setString(2, "UK");
		verify(preparedStatement).close();
		verify(connection).close();
	}
