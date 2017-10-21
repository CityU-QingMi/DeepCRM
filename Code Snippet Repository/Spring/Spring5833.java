	@Test(expected = SpelEvaluationException.class)
	public void testCreateObjectsOnAttemptToReferenceNull() throws Exception {
		TestClass testClass = new TestClass();
		StandardEvaluationContext ctx = new StandardEvaluationContext(testClass);
		ExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration(true, true));
		Object o = null;
		o = parser.parseExpression("wibble.bar").getValue(ctx);
		assertEquals("hello", o);
		o = parser.parseExpression("wibble").getValue(ctx);
		assertNotNull(o);

		o = parser.parseExpression("wibble2.bar").getValue(ctx);
	}
