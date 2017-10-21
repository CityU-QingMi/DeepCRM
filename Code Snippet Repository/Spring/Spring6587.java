	@Test
	public void testQueryWithoutParams() throws SQLException {
		given(resultSet.next()).willReturn(true, false);
		given(resultSet.getInt(1)).willReturn(1);

		SqlQuery<Integer> query = new MappingSqlQueryWithParameters<Integer>() {
			@Override
			protected Integer mapRow(ResultSet rs, int rownum, @Nullable Object[] params, @Nullable Map<? ,?> context)
					throws SQLException {
				assertTrue("params were null", params == null);
				assertTrue("context was null", context == null);
				return rs.getInt(1);
			}
		};
		query.setDataSource(dataSource);
		query.setSql(SELECT_ID);
		query.compile();
		List<Integer> list = query.execute();

		assertThat(list, is(equalTo(Arrays.asList(1))));
		verify(connection).prepareStatement(SELECT_ID);
		verify(resultSet).close();
		verify(preparedStatement).close();
	}
