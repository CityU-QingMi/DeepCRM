	@Test
	public void testUnderMaxRows() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(3);
		given(connection.prepareStatement(UPDATE)).willReturn(preparedStatement);

		MaxRowsUpdater pc = new MaxRowsUpdater();

		int rowsAffected = pc.run();
		assertEquals(3, rowsAffected);
	}
