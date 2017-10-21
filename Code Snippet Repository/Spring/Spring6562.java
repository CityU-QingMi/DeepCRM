	@Test
	public void readAndSplitScriptWithMultipleNewlinesAsSeparator() throws Exception {
		String script = readScript("db-test-data-multi-newline.sql");
		List<String> statements = new ArrayList<>();
		splitSqlScript(script, "\n\n", statements);

		String statement1 = "insert into T_TEST (NAME) values ('Keith')";
		String statement2 = "insert into T_TEST (NAME) values ('Dave')";

		assertEquals("wrong number of statements", 2, statements.size());
		assertEquals("statement 1 not split correctly", statement1, statements.get(0));
		assertEquals("statement 2 not split correctly", statement2, statements.get(1));
	}
