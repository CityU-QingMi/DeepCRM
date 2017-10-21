	@Test
	public void testOverMaxRows() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(8);
		given(connection.prepareStatement(UPDATE)).willReturn(preparedStatement);

		MaxRowsUpdater pc = new MaxRowsUpdater();

		thrown.expect(JdbcUpdateAffectedIncorrectNumberOfRowsException.class);
		pc.run();
	}
