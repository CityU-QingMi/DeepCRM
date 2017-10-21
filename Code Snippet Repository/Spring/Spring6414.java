	@Test
	public void testPreparedStatementSetterFails() throws Exception {
		final String sql = "UPDATE FOO SET NAME=? WHERE ID = 1";
		final String name = "Gary";
		SQLException sqlException = new SQLException();
		given(this.preparedStatement.executeUpdate()).willThrow(sqlException);

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, name);
			}
		};
		this.thrown.expect(DataAccessException.class);
		this.thrown.expect(exceptionCause(sameInstance(sqlException)));
		try {
			new JdbcTemplate(this.dataSource).update(sql, pss);
		}
		finally {
			verify(this.preparedStatement).setString(1, name);
			verify(this.preparedStatement).close();
			verify(this.connection, atLeastOnce()).close();
		}
	}
