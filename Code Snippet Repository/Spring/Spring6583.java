	@Test
	public void testUnnamedParameterDeclarationWithNamedParameterQuery()
			throws SQLException {
		class CustomerQuery extends MappingSqlQuery<Customer> {

			public CustomerQuery(DataSource ds) {
				super(ds, SELECT_ID_FORENAME_WHERE);
				setResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE);
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

			public Customer findCustomer(int id) {
				Map<String, Integer> params = new HashMap<>();
				params.put("id", id);
				return executeByNamedParam(params).get(0);
			}
		}

		// Query should not succeed since parameter declaration did not specify parameter name
		CustomerQuery query = new CustomerQuery(dataSource);
		thrown.expect(InvalidDataAccessApiUsageException.class);
		query.findCustomer(1);
	}
