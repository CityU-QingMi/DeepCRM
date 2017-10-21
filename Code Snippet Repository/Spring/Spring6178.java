	public SqlParameter createReturnResultSetParameter(String parameterName, RowMapper<?> rowMapper) {
		CallMetaDataProvider provider = obtainMetaDataProvider();
		if (provider.isReturnResultSetSupported()) {
			return new SqlReturnResultSet(parameterName, rowMapper);
		}
		else {
			if (provider.isRefCursorSupported()) {
				return new SqlOutParameter(parameterName, provider.getRefCursorSqlType(), rowMapper);
			}
			else {
				throw new InvalidDataAccessApiUsageException("Return of a ResultSet from a stored procedure is not supported.");
			}
		}
	}
