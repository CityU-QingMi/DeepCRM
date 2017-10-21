	@Test
	public void testCreateListsOnAttemptToIndexNull01() throws EvaluationException, ParseException {
		ExpressionParser parser = new SpelExpressionParser(new SpelParserConfiguration(true, true));
		Expression expression = parser.parseExpression("list[0]");
		TestClass testClass = new TestClass();
		Object o = null;
		o = expression.getValue(new StandardEvaluationContext(testClass));
		assertEquals("", o);
		o = parser.parseExpression("list[3]").getValue(new StandardEvaluationContext(testClass));
		assertEquals("", o);
		assertEquals(4, testClass.list.size());
		try {
			o = parser.parseExpression("list2[3]").getValue(new StandardEvaluationContext(testClass));
			fail();
		}
		catch (EvaluationException ee) {
			ee.printStackTrace();
			// success!
		}
		o = parser.parseExpression("foo[3]").getValue(new StandardEvaluationContext(testClass));
		assertEquals("", o);
		assertEquals(4, testClass.getFoo().size());
	}
