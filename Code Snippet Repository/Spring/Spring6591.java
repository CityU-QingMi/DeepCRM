	@Test
	public void testStringQueryWithoutResults() throws SQLException {
		given(resultSet.next()).willReturn(false);
		StringQuery query = new StringQuery(dataSource, SELECT_FORENAME_EMPTY);
		String[] results = query.run();
		assertThat(results, is(equalTo(new String[0])));
		verify(connection).prepareStatement(SELECT_FORENAME_EMPTY);
		verify(resultSet).close();
		verify(preparedStatement).close();
		verify(connection).close();
	}
