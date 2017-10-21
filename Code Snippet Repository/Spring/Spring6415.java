	@Test
	public void testCouldntClose() throws Exception {
		SQLException sqlException = new SQLException("bar");
		given(this.connection.createStatement()).willReturn(this.statement);
		given(this.resultSet.next()).willReturn(false);
		willThrow(sqlException).given(this.resultSet).close();
		willThrow(sqlException).given(this.statement).close();
		willThrow(sqlException).given(this.connection).close();

		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		this.template.query("SELECT ID, FORENAME FROM CUSTMR WHERE ID < 3", rcch);
		verify(this.connection).close();
	}
