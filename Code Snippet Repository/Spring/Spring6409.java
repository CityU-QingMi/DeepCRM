	@Test
	public void testCouldntGetConnectionForOperationOrExceptionTranslator() throws SQLException {
		SQLException sqlException = new SQLException("foo", "07xxx");
		this.dataSource = mock(DataSource.class);
		given(this.dataSource.getConnection()).willThrow(sqlException);
		JdbcTemplate template = new JdbcTemplate(this.dataSource, false);
		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		this.thrown.expect(CannotGetJdbcConnectionException.class);
		this.thrown.expect(exceptionCause(sameInstance(sqlException)));
		template.query("SELECT ID, FORENAME FROM CUSTMR WHERE ID < 3", rcch);
	}
