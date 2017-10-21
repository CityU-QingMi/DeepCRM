	@Test
	public void testBogusUpdate() throws Exception {
		final String sql = "UPDATE NOSUCHTABLE SET DATE_DISPATCHED = SYSDATE WHERE ID = ?";
		final int idParam = 6666;

		// It's because Integers aren't canonical
		SQLException sqlException = new SQLException("bad update");
		given(this.preparedStatement.executeUpdate()).willThrow(sqlException);

		Dispatcher d = new Dispatcher(idParam, sql);
		this.thrown.expect(UncategorizedSQLException.class);
		this.thrown.expect(exceptionCause(equalTo(sqlException)));
		try {
			this.template.update(d);
		}
		finally {
			verify(this.preparedStatement).setInt(1, idParam);
			verify(this.preparedStatement).close();
			verify(this.connection, atLeastOnce()).close();
		}
	}
