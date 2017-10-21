	@Test
	public void testUpdateCustomers() throws SQLException {
		given(resultSet.next()).willReturn(true, true, false);
		given(resultSet.getInt("id")).willReturn(1, 2);
		given(connection.prepareStatement(SELECT_ID_FORENAME_WHERE_ID,
				ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)
			).willReturn(preparedStatement);

		class CustomerUpdateQuery extends UpdatableSqlQuery<Customer> {

			public CustomerUpdateQuery(DataSource ds) {
				super(ds, SELECT_ID_FORENAME_WHERE_ID);
				declareParameter(new SqlParameter(Types.NUMERIC));
				compile();
			}

			@Override
			protected Customer updateRow(ResultSet rs, int rownum, @Nullable Map<? ,?> context)
					throws SQLException {
				rs.updateString(2, "" + context.get(rs.getInt(COLUMN_NAMES[0])));
				return null;
			}
		}

		CustomerUpdateQuery query = new CustomerUpdateQuery(dataSource);
		Map<Integer, String> values = new HashMap<>(2);
		values.put(1, "Rod");
		values.put(2, "Thomas");
		query.execute(2, values);
		verify(resultSet).updateString(2, "Rod");
		verify(resultSet).updateString(2, "Thomas");
		verify(resultSet, times(2)).updateRow();
		verify(preparedStatement).setObject(1, 2, Types.NUMERIC);
		verify(resultSet).close();
		verify(preparedStatement).close();
		verify(connection).close();
	}
