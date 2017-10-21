	@Test
	@TestForIssue(jiraKey = "")
	public void testGetExplicitBinaryTypeName() {
		// lower bound
		String actual = dialect.getTypeName( Types.BINARY, 1, Column.DEFAULT_PRECISION, Column.DEFAULT_SCALE );
		assertEquals(
				"Wrong binary type",
				"char(1) for bit data",
				actual
		);

		// upper bound
		actual = dialect.getTypeName( Types.BINARY, 254, Column.DEFAULT_PRECISION, Column.DEFAULT_SCALE );
		assertEquals(
				"Wrong binary type. 254 is the max length in DB2",
				"char(254) for bit data",
				actual
		);

		// exceeding upper bound
		actual = dialect.getTypeName( Types.BINARY, 255, Column.DEFAULT_PRECISION, Column.DEFAULT_SCALE );
		assertEquals(
				"Wrong binary type. Should be varchar for length > 254",
				"varchar(255) for bit data",
				actual
		);
	}
