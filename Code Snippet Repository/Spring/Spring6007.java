	@Test
	public void booleanLiteral() throws Exception {
		expression = parser.parseExpression("true");
		boolean resultI = expression.getValue(1, Boolean.TYPE);
		assertEquals(true, resultI);
		assertTrue(SpelCompiler.compile(expression));
		boolean resultC = expression.getValue(1, Boolean.TYPE);
		assertEquals(true, resultC);

		expression = parser.parseExpression("false");
		resultI = expression.getValue(1, Boolean.TYPE);
		assertEquals(false, resultI);
		assertTrue(SpelCompiler.compile(expression));
		resultC = expression.getValue(1, Boolean.TYPE);
		assertEquals(false, resultC);
	}
