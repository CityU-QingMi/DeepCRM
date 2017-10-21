	@Test
	public void readAndSplitScriptContainingComments() throws Exception {
		String script = readScript("test-data-with-comments.sql");
		List<String> statements = new ArrayList<>();
		splitSqlScript(script, ';', statements);

		String statement1 = "insert into customer (id, name) values (1, 'Rod; Johnson'), (2, 'Adrian Collier')";
		String statement2 = "insert into orders(id, order_date, customer_id) values (1, '2008-01-02', 2)";
		String statement3 = "insert into orders(id, order_date, customer_id) values (1, '2008-01-02', 2)";
		// Statement 4 addresses the error described in SPR-9982.
		String statement4 = "INSERT INTO persons( person_id , name) VALUES( 1 , 'Name' )";

		assertEquals("wrong number of statements", 4, statements.size());
		assertEquals("statement 1 not split correctly", statement1, statements.get(0));
		assertEquals("statement 2 not split correctly", statement2, statements.get(1));
		assertEquals("statement 3 not split correctly", statement3, statements.get(2));
		assertEquals("statement 4 not split correctly", statement4, statements.get(3));
	}
