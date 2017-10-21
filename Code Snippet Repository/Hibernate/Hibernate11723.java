	@Override
	public IsolationDelegate createIsolationDelegate() {
		Connection connection = mock(Connection.class);
		JdbcConnectionAccess jdbcConnectionAccess = mock(JdbcConnectionAccess.class);
		try {
			when(jdbcConnectionAccess.obtainConnection()).thenReturn(connection);
		} catch (SQLException e) {
		}
		return new JtaIsolationDelegate(jdbcConnectionAccess, mock(SqlExceptionHelper.class), tm);
	}
