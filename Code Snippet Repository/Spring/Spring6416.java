	@Test
	public void testFatalWarning() throws Exception {
		String sql = "SELECT forename from custmr";
		SQLWarning warnings = new SQLWarning("My warning");

		given(this.resultSet.next()).willReturn(false);
		given(this.preparedStatement.getWarnings()).willReturn(warnings);
		given(this.connection.createStatement()).willReturn(this.preparedStatement);

		JdbcTemplate t = new JdbcTemplate(this.dataSource);
		t.setIgnoreWarnings(false);
		this.thrown.expect(SQLWarningException.class);
		this.thrown.expect(exceptionCause(sameInstance(warnings)));
		try {
			t.query(sql, new RowCallbackHandler() {
				@Override
				public void processRow(ResultSet rs) throws SQLException {
					rs.getByte(1);
				}
			});
		}
		finally {
			verify(this.resultSet).close();
			verify(this.preparedStatement).close();
			verify(this.connection).close();
		}
	}
