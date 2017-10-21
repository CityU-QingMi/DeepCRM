	@Test
	public void variableReference_root() throws Exception {
		String s = "hello";
		Expression expression = parser.parseExpression("#root");
		String resultI = expression.getValue(s, String.class);
		assertCanCompile(expression);
		String resultC = expression.getValue(s, String.class);
		assertEquals(s, resultI);
		assertEquals(s, resultC);

		expression = parser.parseExpression("#root");
		int i = (Integer) expression.getValue(42);
		assertEquals(42,i);
		assertCanCompile(expression);
		i = (Integer) expression.getValue(42);
		assertEquals(42,i);
	}
