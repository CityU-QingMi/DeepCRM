	@Override
	public ResultSet extract(PreparedStatement statement) {
		// IMPL NOTE : SQL logged by caller
		try {
			final ResultSet rs;
			try {
				jdbcExecuteStatementStart();
				rs = statement.executeQuery();
			}
			finally {
				jdbcExecuteStatementEnd();
			}
			postExtract( rs, statement );
			return rs;
		}
		catch (SQLException e) {
			throw sqlExceptionHelper.convert( e, "could not extract ResultSet" );
		}
	}
