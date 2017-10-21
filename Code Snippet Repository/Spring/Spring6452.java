	@Test
	public void testQueryWithResultSetExtractorNoParameters() throws SQLException {
		given(resultSet.next()).willReturn(true);
		given(resultSet.getInt("id")).willReturn(1);
		given(resultSet.getString("forename")).willReturn("rod");

		Customer cust = namedParameterTemplate.query(SELECT_NO_PARAMETERS,
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
		verify(connection).prepareStatement(SELECT_NO_PARAMETERS);
		verify(preparedStatement).close();
		verify(connection).close();
	}
