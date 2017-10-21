	@Override
	public ResultSet extract(CallableStatement callableStatement) {
		// IMPL NOTE : SQL logged by caller
		try {
			final ResultSet rs;
			try {
				jdbcExecuteStatementStart();
				rs = dialect.getResultSet( callableStatement );
			}
			finally {
				jdbcExecuteStatementEnd();
			}
			postExtract( rs, callableStatement );
			return rs;
		}
		catch (SQLException e) {
			throw sqlExceptionHelper.convert( e, "could not extract ResultSet" );
		}
	}
