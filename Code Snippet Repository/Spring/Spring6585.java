	@Test
	public void testNamedParameterUsingInvalidQuestionMarkPlaceHolders()
			throws SQLException {
		given(
		connection.prepareStatement(SELECT_ID_FORENAME_WHERE_ID_REUSED_1,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)).willReturn(preparedStatement);

		class CustomerQuery extends MappingSqlQuery<Customer> {

			public CustomerQuery(DataSource ds) {
				super(ds, SELECT_ID_FORENAME_WHERE_ID_REUSED_1);
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

			public List<Customer> findCustomers(Integer id1) {
				Map<String, Integer> params = new HashMap<>();
				params.put("id1", id1);
				return executeByNamedParam(params);
			}
		}

		CustomerQuery query = new CustomerQuery(dataSource);
		thrown.expect(InvalidDataAccessApiUsageException.class);
		query.findCustomers(1);
	}
