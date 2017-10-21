	@Test
	public void testBatchUpdateFails() throws Exception {
		final String sql = "UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = ?";
		final int[] ids = new int[] { 100, 200 };
		SQLException sqlException = new SQLException();

		given(this.preparedStatement.executeBatch()).willThrow(sqlException);
		mockDatabaseMetaData(true);

		BatchPreparedStatementSetter setter = new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setInt(1, ids[i]);
			}
			@Override
			public int getBatchSize() {
				return ids.length;
			}
		};

		this.thrown.expect(DataAccessException.class);
		this.thrown.expect(exceptionCause(sameInstance(sqlException)));
		try {
			this.template.batchUpdate(sql, setter);
		}
		finally {
			verify(this.preparedStatement, times(2)).addBatch();
			verify(this.preparedStatement).setInt(1, ids[0]);
			verify(this.preparedStatement).setInt(1, ids[1]);
			verify(this.preparedStatement).close();
			verify(this.connection, atLeastOnce()).close();
		}
	}
