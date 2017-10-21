	@Test
	public void testNamedParameterQueryReusingParameter() throws SQLException {
		given(resultSet.next()).willReturn(true, true, false);
		given(resultSet.getInt("id")).willReturn(1, 2);
		given(resultSet.getString("forename")).willReturn("rod", "juergen");

		given(connection.prepareStatement(SELECT_ID_FORENAME_WHERE_ID_REUSED_1,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)).willReturn(preparedStatement)
;

		class CustomerQuery extends MappingSqlQuery<Customer> {

			public CustomerQuery(DataSource ds) {
				super(ds, SELECT_ID_FORENAME_WHERE_ID_REUSED_2);
				setResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE);
				declareParameter(new SqlParameter("id1", Types.NUMERIC));
				compile();
			}

			@Override
			protected Customer mapRow(ResultSet rs, int rownum) throws SQLException {
				Customer cust = new Customer();
				cust.setId(rs.getInt(COLUMN_NAMES[0]));
				cust.setForename(rs.getString(COLUMN_NAMES[1]));
				return cust;
			}

			public List<Customer> findCustomers(Integer id) {
				Map<String, Object> params = new HashMap<>();
				params.put("id1", id);
				return executeByNamedParam(params);
			}
		}

		CustomerQuery query = new CustomerQuery(dataSource);
		List<Customer> cust = query.findCustomers(1);

		assertEquals("We got two customers back", cust.size(), 2);
		assertEquals("First customer id was assigned correctly", cust.get(0).getId(), 1);
		assertEquals("First customer forename was assigned correctly", cust.get(0).getForename(), "rod");
		assertEquals("Second customer id was assigned correctly", cust.get(1).getId(), 2);
		assertEquals("Second customer forename was assigned correctly", cust.get(1).getForename(), "juergen");

		verify(preparedStatement).setObject(1, 1, Types.NUMERIC);
		verify(preparedStatement).setObject(2, 1, Types.NUMERIC);
		verify(resultSet).close();
		verify(preparedStatement).close();
		verify(connection).close();
	}
