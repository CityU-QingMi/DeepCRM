	@Test
	public void queryWithArgsAndTypesAndRowMapper() throws SQLException {
		result = template.query("some SQL",
				new Object[] { "test1", "test2" },
				new int[] { Types.VARCHAR, Types.VARCHAR },
				testRowMapper);
		verify(preparedStatement).setString(1, "test1");
		verify(preparedStatement).setString(2, "test2");
		verify(preparedStatement).close();
	}
