	@Test
	public void testBatchUpdate() throws Exception {
		final String[] sql = {"UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = 1",
				"UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = 2"};

		given(this.statement.executeBatch()).willReturn(new int[] {1, 1});
		mockDatabaseMetaData(true);
		given(this.connection.createStatement()).willReturn(this.statement);

		JdbcTemplate template = new JdbcTemplate(this.dataSource, false);

		int[] actualRowsAffected = template.batchUpdate(sql);
		assertTrue("executed 2 updates", actualRowsAffected.length == 2);

		verify(this.statement).addBatch(sql[0]);
		verify(this.statement).addBatch(sql[1]);
		verify(this.statement).close();
		verify(this.connection, atLeastOnce()).close();
	}
