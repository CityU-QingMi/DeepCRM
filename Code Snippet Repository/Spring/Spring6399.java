	@Test
	public void testBatchUpdateWithPreparedStatement() throws Exception {
		final String sql = "UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = ?";
		final int[] ids = new int[] { 100, 200 };
		final int[] rowsAffected = new int[] { 1, 2 };

		given(this.preparedStatement.executeBatch()).willReturn(rowsAffected);
		mockDatabaseMetaData(true);

		BatchPreparedStatementSetter setter =
			new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i)
				throws SQLException {
				ps.setInt(1, ids[i]);
			}
			@Override
			public int getBatchSize() {
				return ids.length;
			}
		};

		JdbcTemplate template = new JdbcTemplate(this.dataSource, false);

		int[] actualRowsAffected = template.batchUpdate(sql, setter);
		assertTrue("executed 2 updates", actualRowsAffected.length == 2);
		assertEquals(rowsAffected[0], actualRowsAffected[0]);
		assertEquals(rowsAffected[1], actualRowsAffected[1]);

		verify(this.preparedStatement, times(2)).addBatch();
		verify(this.preparedStatement).setInt(1, ids[0]);
		verify(this.preparedStatement).setInt(1, ids[1]);
		verify(this.preparedStatement).close();
		verify(this.connection, atLeastOnce()).close();
	}
