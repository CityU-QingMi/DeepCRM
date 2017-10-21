	@Test(expected = SpelEvaluationException.class)
	public void testCreateMapsOnAttemptToIndexNull01() throws Exception {
		TestClass testClass = new TestClass();
		StandardEvaluationContext ctx = new StandardEvaluationContext(testClass);
		ExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration(true, true));
		Object o = null;
		o = parser.parseExpression("map['a']").getValue(ctx);
		assertNull(o);
		o = parser.parseExpression("map").getValue(ctx);
		assertNotNull(o);

		o = parser.parseExpression("map2['a']").getValue(ctx);
		// map2 should be null, there is no setter
	}
