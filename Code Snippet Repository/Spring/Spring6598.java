	@Test
	public void testRequiredRows() throws SQLException {
		given(preparedStatement.executeUpdate()).willReturn(3);
		given(connection.prepareStatement(UPDATE)).willReturn(preparedStatement);

		RequiredRowsUpdater pc = new RequiredRowsUpdater();
		int rowsAffected = pc.run();

		assertEquals(3, rowsAffected);
	}
