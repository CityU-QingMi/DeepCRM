	public static int[] executeBatchUpdate(
			String sql, final List<Object[]> batchValues, final int[] columnTypes, JdbcOperations jdbcOperations) {

		return jdbcOperations.batchUpdate(
				sql,
				new BatchPreparedStatementSetter() {
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						Object[] values = batchValues.get(i);
						setStatementParameters(values, ps, columnTypes);
					}
					@Override
					public int getBatchSize() {
						return batchValues.size();
					}
				});
	}
