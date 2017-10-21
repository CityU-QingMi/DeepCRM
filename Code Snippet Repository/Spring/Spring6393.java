	@Test
	public void testSqlUpdateEncountersSqlException() throws Exception {
		SQLException sqlException = new SQLException("bad update");
		final String sql = "UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = 4";

		given(this.statement.executeUpdate(sql)).willThrow(sqlException);
		given(this.connection.createStatement()).willReturn(this.statement);

		this.thrown.expect(exceptionCause(sameInstance(sqlException)));
		try {
			this.template.update(sql);
		}
		finally {
			verify(this.statement).close();
			verify(this.connection, atLeastOnce()).close();
		}
	}
