	@Test
	public void testCouldntGetConnectionForOperationWithLazyExceptionTranslator() throws SQLException {
		SQLException sqlException = new SQLException("foo", "07xxx");
		this.dataSource = mock(DataSource.class);
		given(this.dataSource.getConnection()).willThrow(sqlException);
		this.template = new JdbcTemplate();
		this.template.setDataSource(this.dataSource);
		this.template.afterPropertiesSet();
		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		this.thrown.expect(CannotGetJdbcConnectionException.class);
		this.thrown.expect(exceptionCause(sameInstance(sqlException)));
		this.template.query("SELECT ID, FORENAME FROM CUSTMR WHERE ID < 3", rcch);
	}
