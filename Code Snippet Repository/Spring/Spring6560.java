	@Test
	public void splitSqlScriptDelimitedWithNewLineButDefaultDelimiterSpecified() {
		String statement1 = "do something";
		String statement2 = "do something else";
		char delim = '\n';
		String script = statement1 + delim + statement2 + delim;
		List<String> statements = new ArrayList<>();
		splitSqlScript(script, DEFAULT_STATEMENT_SEPARATOR, statements);
		assertEquals("wrong number of statements", 1, statements.size());
		assertEquals("script should have been 'stripped' but not actually 'split'", script.replace('\n', ' '),
			statements.get(0));
	}
