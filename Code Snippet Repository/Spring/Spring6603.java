	@Test
	public void testUpdateMixed() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(1);
		given(connection.prepareStatement(UPDATE_OBJECTS)).willReturn(preparedStatement);

		MixedUpdater pc = new MixedUpdater();
		int rowsAffected = pc.run(1, 1, "rod", true);

		assertEquals(1, rowsAffected);
		verify(preparedStatement).setObject(1, 1, Types.NUMERIC);
		verify(preparedStatement).setObject(2, 1, Types.NUMERIC, 2);
		verify(preparedStatement).setString(3, "rod");
		verify(preparedStatement).setBoolean(4, Boolean.TRUE);
	}
