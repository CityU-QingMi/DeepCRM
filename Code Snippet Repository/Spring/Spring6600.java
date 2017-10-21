	@Test
	public void testUpdateIntInt() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(1);
		given(connection.prepareStatement(UPDATE_INT_INT)).willReturn(preparedStatement);

		IntIntUpdater pc = new IntIntUpdater();
		int rowsAffected = pc.run(1, 1);

		assertEquals(1, rowsAffected);
		verify(preparedStatement).setObject(1, 1, Types.NUMERIC);
		verify(preparedStatement).setObject(2, 1, Types.NUMERIC);
	}
