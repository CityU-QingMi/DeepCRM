	@Test
	public void testBatchUpdateWithBatchFailure() throws Exception {
		final String[] sql = {"A", "B", "C", "D"};
		given(this.statement.executeBatch()).willThrow(
				new BatchUpdateException(new int[] { 1, Statement.EXECUTE_FAILED, 1,
					Statement.EXECUTE_FAILED }));
		mockDatabaseMetaData(true);
		given(this.connection.createStatement()).willReturn(this.statement);

		JdbcTemplate template = new JdbcTemplate(this.dataSource, false);
		try {
			template.batchUpdate(sql);
		}
		catch (UncategorizedSQLException ex) {
			assertThat(ex.getSql(), equalTo("B; D"));
		}
	}
