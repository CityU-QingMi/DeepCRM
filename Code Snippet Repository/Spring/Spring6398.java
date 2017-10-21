	@Test
	public void testBatchUpdateWithNoBatchSupportAndSelect() throws Exception {
		final String[] sql = {"UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = 1",
				"SELECT * FROM NOSUCHTABLE"};

		given(this.statement.execute(sql[0])).willReturn(false);
		given(this.statement.getUpdateCount()).willReturn(1);
		given(this.statement.execute(sql[1])).willReturn(true);
		mockDatabaseMetaData(false);
		given(this.connection.createStatement()).willReturn(this.statement);

		JdbcTemplate template = new JdbcTemplate(this.dataSource, false);
		this.thrown.expect(InvalidDataAccessApiUsageException.class);
		try {
			template.batchUpdate(sql);
		}
		finally {
			verify(this.statement, never()).addBatch(anyString());
			verify(this.statement).close();
			verify(this.connection, atLeastOnce()).close();
		}
	}
