	private void doTestCouldntGetConnectionInOperationWithExceptionTranslatorInitialized(boolean beanProperty)
			throws SQLException {

		SQLException sqlException = new SQLException("foo", "07xxx");
		this.dataSource = mock(DataSource.class);
		given(this.dataSource.getConnection()).willThrow(sqlException);
		this.template = new JdbcTemplate();
		this.template.setDataSource(this.dataSource);
		this.template.setLazyInit(false);
		if (beanProperty) {
			// This will get a connection.
			this.template.setExceptionTranslator(new SQLErrorCodeSQLExceptionTranslator(this.dataSource));
		}
		else {
			// This will cause creation of default SQL translator.
			this.template.afterPropertiesSet();
		}
		RowCountCallbackHandler rcch = new RowCountCallbackHandler();
		this.thrown.expect(CannotGetJdbcConnectionException.class);
		this.thrown.expect(exceptionCause(sameInstance(sqlException)));
		this.template.query("SELECT ID, FORENAME FROM CUSTMR WHERE ID < 3", rcch);
	}
