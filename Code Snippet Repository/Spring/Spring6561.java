	@Test
	public void splitScriptWithSingleQuotesNestedInsideDoubleQuotes() throws Exception {
		String statement1 = "select '1' as \"Dogbert's owner's\" from dual";
		String statement2 = "select '2' as \"Dilbert's\" from dual";
		char delim = ';';
		String script = statement1 + delim + statement2 + delim;
		List<String> statements = new ArrayList<>();
		splitSqlScript(script, ';', statements);
		assertEquals("wrong number of statements", 2, statements.size());
		assertEquals("statement 1 not split correctly", statement1, statements.get(0));
		assertEquals("statement 2 not split correctly", statement2, statements.get(1));
	}
