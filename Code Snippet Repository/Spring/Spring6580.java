	@Test
	public void testListCustomersIntInt() throws SQLException {
		given(resultSet.next()).willReturn(true, true, false);
		given(resultSet.getInt("id")).willReturn(1, 2);
		given(resultSet.getString("forename")).willReturn("rod", "dave");

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
		}

		CustomerQuery query = new CustomerQuery(dataSource);
		List<Customer> list = query.execute(1, 1);
		assertTrue("2 results in list", list.size() == 2);
		assertThat(list.get(0).getForename(), is("rod"));
		assertThat(list.get(1).getForename(), is("dave"));
		verify(preparedStatement).setObject(1, 1, Types.NUMERIC);
		verify(preparedStatement).setObject(2, 1, Types.NUMERIC);
		verify(connection).prepareStatement(SELECT_ID_WHERE);
		verify(resultSet).close();
		verify(preparedStatement).close();
		verify(connection).close();
	}
