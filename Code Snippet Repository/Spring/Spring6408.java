	@Test
	public void testBatchUpdateWithCollectionOfObjects() throws Exception {
		final String sql = "UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = ?";
		final List<Integer> ids = Arrays.asList(100, 200, 300);
		final int[] rowsAffected1 = new int[] { 1, 2 };
		final int[] rowsAffected2 = new int[] { 3 };

		given(this.preparedStatement.executeBatch()).willReturn(rowsAffected1, rowsAffected2);
		mockDatabaseMetaData(true);

		ParameterizedPreparedStatementSetter<Integer> setter = new ParameterizedPreparedStatementSetter<Integer>() {
			@Override
			public void setValues(PreparedStatement ps, Integer argument) throws SQLException {
				ps.setInt(1, argument.intValue());
			}
		};

		JdbcTemplate template = new JdbcTemplate(this.dataSource, false);

		int[][] actualRowsAffected = template.batchUpdate(sql, ids, 2, setter);
		assertTrue("executed 2 updates", actualRowsAffected[0].length == 2);
		assertEquals(rowsAffected1[0], actualRowsAffected[0][0]);
		assertEquals(rowsAffected1[1], actualRowsAffected[0][1]);
		assertEquals(rowsAffected2[0], actualRowsAffected[1][0]);

		verify(this.preparedStatement, times(3)).addBatch();
		verify(this.preparedStatement).setInt(1, ids.get(0));
		verify(this.preparedStatement).setInt(1, ids.get(1));
		verify(this.preparedStatement).setInt(1, ids.get(2));
		verify(this.preparedStatement).close();
		verify(this.connection, atLeastOnce()).close();
	}
