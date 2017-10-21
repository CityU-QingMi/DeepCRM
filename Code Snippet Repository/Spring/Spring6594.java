	@Test
	public void testFindTooManyCustomers() throws SQLException {
		given(resultSet.next()).willReturn(true, true, false);
		given(resultSet.getInt("id")).willReturn(1, 2);
		given(resultSet.getString("forename")).willReturn("rod", "rod");

		class CustomerQuery extends MappingSqlQuery<Customer> {

			public CustomerQuery(DataSource ds) {
				super(ds, SELECT_ID_FORENAME_WHERE);
				declareParameter(new SqlParameter(Types.VARCHAR));
				compile();
			}

			@Override
			protected Customer mapRow(ResultSet rs, int rownum) throws SQLException {
				Customer cust = new Customer();
				cust.setId(rs.getInt(COLUMN_NAMES[0]));
				cust.setForename(rs.getString(COLUMN_NAMES[1]));
				return cust;
			}

			public Customer findCustomer(String id) {
				return findObject(id);
			}
		}

		CustomerQuery query = new CustomerQuery(dataSource);
		thrown.expect(IncorrectResultSizeDataAccessException.class);
		try {
			query.findCustomer("rod");
		}
		finally {
			verify(preparedStatement).setString(1, "rod");
			verify(connection).prepareStatement(SELECT_ID_FORENAME_WHERE);
			verify(resultSet).close();
			verify(preparedStatement).close();
			verify(connection).close();
		}
	}
