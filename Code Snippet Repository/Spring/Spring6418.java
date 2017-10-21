	@Test
	public void testSQLErrorCodeTranslation() throws Exception {
		final SQLException sqlException = new SQLException("I have a known problem", "99999", 1054);
		final String sql = "SELECT ID FROM CUSTOMER";

		given(this.resultSet.next()).willReturn(true);
		mockDatabaseMetaData(false);
		given(this.connection.createStatement()).willReturn(this.preparedStatement);

		this.thrown.expect(BadSqlGrammarException.class);
		this.thrown.expect(exceptionCause(sameInstance(sqlException)));
		try {
			this.template.query(sql, new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					throw sqlException;
				}
			});
			fail("Should have thrown BadSqlGrammarException");
		}
		finally {
			verify(this.resultSet).close();
			verify(this.preparedStatement).close();
			verify(this.connection, atLeastOnce()).close();
		}
	}
