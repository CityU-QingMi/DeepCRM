	@Test
	public void nullLiteral() throws Exception {
		expression = parser.parseExpression("null");
		Object resultI = expression.getValue(new TestClass1(), Object.class);
		assertCanCompile(expression);
		Object resultC = expression.getValue(new TestClass1(), Object.class);
		assertEquals(null, resultI);
		assertEquals(null, resultC);
		assertEquals(null, resultC);
	}
