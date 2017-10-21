	@Test
	public void stringLiteral() throws Exception {
		expression = parser.parseExpression("'abcde'");
		assertEquals("abcde", expression.getValue(new TestClass1(), String.class));
		assertCanCompile(expression);
		String resultC = expression.getValue(new TestClass1(), String.class);
		assertEquals("abcde", resultC);
		assertEquals("abcde", expression.getValue(String.class));
		assertEquals("abcde", expression.getValue());
		assertEquals("abcde", expression.getValue(new StandardEvaluationContext()));
		expression = parser.parseExpression("\"abcde\"");
		assertCanCompile(expression);
		assertEquals("abcde", expression.getValue(String.class));
	}
