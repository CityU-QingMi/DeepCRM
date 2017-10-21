	@Test
	public void indexerMapAccessor_12045() throws Exception {
		SpelParserConfiguration spc = new SpelParserConfiguration(
				SpelCompilerMode.IMMEDIATE,getClass().getClassLoader());
		SpelExpressionParser sep = new SpelExpressionParser(spc);
		expression=sep.parseExpression("headers[command]");
		MyMessage root = new MyMessage();
		assertEquals("wibble", expression.getValue(root));
		// This next call was failing because the isCompilable check in Indexer
		// did not check on the key being compilable (and also generateCode in the
		// Indexer was missing the optimization that it didn't need necessarily
		// need to call generateCode for that accessor)
		assertEquals("wibble", expression.getValue(root));
		assertCanCompile(expression);

		// What about a map key that is an expression - ensure the getKey() is evaluated in the right scope
		expression=sep.parseExpression("headers[getKey()]");
		assertEquals("wobble", expression.getValue(root));
		assertEquals("wobble", expression.getValue(root));

		expression=sep.parseExpression("list[getKey2()]");
		assertEquals("wobble", expression.getValue(root));
		assertEquals("wobble", expression.getValue(root));

		expression = sep.parseExpression("ia[getKey2()]");
		assertEquals(3, expression.getValue(root));
		assertEquals(3, expression.getValue(root));
	}
