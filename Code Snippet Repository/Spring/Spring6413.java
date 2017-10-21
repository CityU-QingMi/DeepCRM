	@Test
	public void testPreparedStatementSetterSucceeds() throws Exception {
		final String sql = "UPDATE FOO SET NAME=? WHERE ID = 1";
		final String name = "Gary";
		int expectedRowsUpdated = 1;

		given(this.preparedStatement.executeUpdate()).willReturn(expectedRowsUpdated);

		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, name);
			}
		};
		int actualRowsUpdated = new JdbcTemplate(this.dataSource).update(sql, pss);
		assertTrue("updated correct # of rows", actualRowsUpdated == expectedRowsUpdated);
		verify(this.preparedStatement).setString(1, name);
		verify(this.preparedStatement).close();
		verify(this.connection).close();
	}
