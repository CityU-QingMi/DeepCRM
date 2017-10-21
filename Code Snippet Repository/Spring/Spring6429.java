	@Test
	public void testCloseConnectionOnRequest() throws Exception {
		String sql = "SELECT ID, FORENAME FROM CUSTMR WHERE ID < 3";

		given(this.resultSet.next()).willReturn(false);
		given(this.connection.createStatement()).willReturn(this.preparedStatement);

		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		this.template.query(sql, rcch);

		verify(this.resultSet).close();
		verify(this.preparedStatement).close();
		verify(this.connection).close();
	}
