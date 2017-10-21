	public static int[] executeBatchUpdateWithNamedParameters(final ParsedSql parsedSql,
			final SqlParameterSource[] batchArgs, JdbcOperations jdbcOperations) {
		if (batchArgs.length <= 0) {
			return new int[] {0};
		}
		String sqlToUse = NamedParameterUtils.substituteNamedParameters(parsedSql, batchArgs[0]);
		return jdbcOperations.batchUpdate(
				sqlToUse,
				new BatchPreparedStatementSetter() {

					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						Object[] values = NamedParameterUtils.buildValueArray(parsedSql, batchArgs[i], null);
						int[] columnTypes = NamedParameterUtils.buildSqlTypeArray(parsedSql, batchArgs[i]);
						setStatementParameters(values, ps, columnTypes);
					}

					@Override
					public int getBatchSize() {
						return batchArgs.length;
					}
				});
	}
