	@Test
	@TestForIssue(jiraKey = "")
	public void testGetDefaultBinaryTypeName() {
		String actual = dialect.getTypeName( Types.BINARY );
		assertEquals(
				"The default column length is 255, but char length on DB2 is limited to 254",
				"varchar($l) for bit data",
				actual
		);
	}
