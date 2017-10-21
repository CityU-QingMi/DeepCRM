	@Test
	public void testUpdateString() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(1);
		given(connection.prepareStatement(UPDATE_STRING)).willReturn(preparedStatement);

		StringUpdater pc = new StringUpdater();
		int rowsAffected = pc.run("rod");

		assertEquals(1, rowsAffected);
		verify(preparedStatement).setString(1, "rod");
	}
