	@Test
	public void testFindCustomerIntInt() throws SQLException {
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt("id")).willReturn(1);
		given(resultSet.getString("forename")).willReturn("rod");

		class CustomerQuery extends MappingSqlQuery<Customer> {

			public CustomerQuery(DataSource ds) {
				super(ds, SELECT_ID_WHERE);
				declareParameter(new SqlParameter(Types.NUMERIC));
				declareParameter(new SqlParameter(Types.NUMERIC));
				compile();
			}

			@Override
			protected Customer mapRow(ResultSet rs, int rownum) throws SQLException {
				Customer cust = new Customer();
				cust.setId(rs.getInt(COLUMN_NAMES[0]));
				cust.setForename(rs.getString(COLUMN_NAMES[1]));
				return cust;
			}

			public Customer findCustomer(int id, int otherNum) {
				return findObject(id, otherNum);
			}
		}

		CustomerQuery query = new CustomerQuery(dataSource);
		Customer cust = query.findCustomer(1, 1);

		assertTrue("Customer id was assigned correctly", cust.getId() == 1);
		assertTrue("Customer forename was assigned correctly", cust.getForename().equals("rod"));
		verify(preparedStatement).setObject(1, 1, Types.NUMERIC);
		verify(preparedStatement).setObject(2, 1, Types.NUMERIC);
		verify(connection).prepareStatement(SELECT_ID_WHERE);
		verify(resultSet).close();
		verify(preparedStatement).close();
		verify(connection).close();
	}
