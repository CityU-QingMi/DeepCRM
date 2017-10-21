	@Test
	public void testUpdateInt() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(1);
		given(connection.prepareStatement(UPDATE_INT)).willReturn(preparedStatement);

		IntUpdater pc = new IntUpdater();
		int rowsAffected = pc.run(1);

		assertEquals(1, rowsAffected);
		verify(preparedStatement).setObject(1, 1, Types.NUMERIC);
	}
