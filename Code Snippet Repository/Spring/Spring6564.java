	@Test
	public void readAndSplitScriptContainingCommentsWithLeadingTabs() throws Exception {
		String script = readScript("test-data-with-comments-and-leading-tabs.sql");
		List<String> statements = new ArrayList<>();
		splitSqlScript(script, ';', statements);

		String statement1 = "insert into customer (id, name) values (1, 'Sam Brannen')";
		String statement2 = "insert into orders(id, order_date, customer_id) values (1, '2013-06-08', 1)";
		String statement3 = "insert into orders(id, order_date, customer_id) values (2, '2013-06-08', 1)";

		assertEquals("wrong number of statements", 3, statements.size());
		assertEquals("statement 1 not split correctly", statement1, statements.get(0));
		assertEquals("statement 2 not split correctly", statement2, statements.get(1));
		assertEquals("statement 3 not split correctly", statement3, statements.get(2));
	}
