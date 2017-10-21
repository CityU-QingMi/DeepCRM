	@Test
	public void testStringQueryWithResults() throws Exception {
		String[] dbResults = new String[] { "alpha", "beta", "charlie" };
		given(resultSet.next()).willReturn(true, true, true, false);
		given(resultSet.getString(1)).willReturn(dbResults[0], dbResults[1], dbResults[2]);
		StringQuery query = new StringQuery(dataSource, SELECT_FORENAME);
		query.setRowsExpected(3);
		String[] results = query.run();
		assertThat(results, is(equalTo(dbResults)));
		verify(connection).prepareStatement(SELECT_FORENAME);
		verify(resultSet).close();
		verify(preparedStatement).close();
		verify(connection).close();
	}
