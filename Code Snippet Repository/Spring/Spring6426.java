	@Test
	public void testLeaveConnectionOpenOnRequest() throws Exception {
		String sql = "SELECT ID, FORENAME FROM CUSTMR WHERE ID < 3";

		given(this.resultSet.next()).willReturn(false);
		given(this.connection.isClosed()).willReturn(false);
		given(this.connection.createStatement()).willReturn(this.preparedStatement);
		// if close is called entire test will fail
		willThrow(new RuntimeException()).given(this.connection).close();

		SingleConnectionDataSource scf = new SingleConnectionDataSource(this.dataSource.getConnection(), false);
		this.template = new JdbcTemplate(scf, false);
		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		this.template.query(sql, rcch);

		verify(this.resultSet).close();
		verify(this.preparedStatement).close();
	}
