	@Override
	public int executeUpdate(PreparedStatement statement) {
		try {
			jdbcExecuteStatementStart();
			return statement.executeUpdate();
		}
		catch (SQLException e) {
			throw sqlExceptionHelper.convert( e, "could not execute statement" );
		}
		finally {
			jdbcExecuteStatementEnd();
		}
	}
