	@Test
	public void readAndSplitScriptContainingMuliLineComments() throws Exception {
		String script = readScript("test-data-with-multi-line-comments.sql");
		List<String> statements = new ArrayList<>();
		splitSqlScript(script, ';', statements);

		String statement1 = "INSERT INTO users(first_name, last_name) VALUES('Juergen', 'Hoeller')";
		String statement2 = "INSERT INTO users(first_name, last_name) VALUES( 'Sam' , 'Brannen' )";

		assertEquals("wrong number of statements", 2, statements.size());
		assertEquals("statement 1 not split correctly", statement1, statements.get(0));
		assertEquals("statement 2 not split correctly", statement2, statements.get(1));
	}
