	@Test
	public void testListCustomersString() throws SQLException {
		given(resultSet.next()).willReturn(true, true, false);
		given(resultSet.getInt("id")).willReturn(1, 2);
		given(resultSet.getString("forename")).willReturn("rod", "dave");

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
		}

		CustomerQuery query = new CustomerQuery(dataSource);
		List<Customer> list = query.execute("one");
		assertTrue("2 results in list", list.size() == 2);
		assertThat(list.get(0).getForename(), is("rod"));
		assertThat(list.get(1).getForename(), is("dave"));
		verify(preparedStatement).setString(1, "one");
		verify(connection).prepareStatement(SELECT_ID_FORENAME_WHERE);
		verify(resultSet).close();
		verify(preparedStatement).close();
		verify(connection).close();
	}
