	@Test
	public void testBatchUpdateWithNoBatchSupport() throws Exception {
		final String[] sql = {"UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = 1",
				"UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = 2"};

		given(this.statement.execute(sql[0])).willReturn(false);
		given(this.statement.getUpdateCount()).willReturn(1, 1);
		given(this.statement.execute(sql[1])).willReturn(false);

		mockDatabaseMetaData(false);
		given(this.connection.createStatement()).willReturn(this.statement);

		JdbcTemplate template = new JdbcTemplate(this.dataSource, false);

		int[] actualRowsAffected = template.batchUpdate(sql);
		assertTrue("executed 2 updates", actualRowsAffected.length == 2);

		verify(this.statement, never()).addBatch(anyString());
		verify(this.statement).close();
		verify(this.connection, atLeastOnce()).close();
	}
