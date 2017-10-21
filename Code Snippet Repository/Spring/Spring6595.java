	@Test
	public void testUpdate() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(1);
		given(connection.prepareStatement(UPDATE)).willReturn(preparedStatement);

		Updater pc = new Updater();
		int rowsAffected = pc.run();

		assertEquals(1, rowsAffected);
	}
