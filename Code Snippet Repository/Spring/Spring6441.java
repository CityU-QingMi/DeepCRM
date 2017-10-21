	@Test
	public void testQueryWithRowMapperNoParameters() throws SQLException {
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt("id")).willReturn(1);
		given(resultSet.getString("forename")).willReturn("rod");

		List<Customer> customers = namedParameterTemplate.query(SELECT_NO_PARAMETERS,
				new RowMapper<Customer>() {
					@Override
					public Customer mapRow(ResultSet rs, int rownum) throws SQLException {
						Customer cust = new Customer();
						cust.setId(rs.getInt(COLUMN_NAMES[0]));
						cust.setForename(rs.getString(COLUMN_NAMES[1]));
						return cust;
					}
				});
		assertEquals(1, customers.size());
		assertTrue("Customer id was assigned correctly", customers.get(0).getId() == 1);
		assertTrue("Customer forename was assigned correctly", customers.get(0).getForename().equals("rod"));
		verify(connection).prepareStatement(SELECT_NO_PARAMETERS);
		verify(preparedStatement).close();
		verify(connection).close();
	}
