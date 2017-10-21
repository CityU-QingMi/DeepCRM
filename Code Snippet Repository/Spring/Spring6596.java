	@Test
	public void testMaxRows() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(5);
		given(connection.prepareStatement(UPDATE)).willReturn(preparedStatement);

		MaxRowsUpdater pc = new MaxRowsUpdater();
		int rowsAffected = pc.run();

		assertEquals(5, rowsAffected);
	}
