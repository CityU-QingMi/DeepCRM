	@Test
	public void ternaryWithBooleanReturn() { // SPR-12271
		expression = parser.parseExpression("T(Boolean).TRUE?'abc':'def'");
		assertEquals("abc", expression.getValue());
		assertCanCompile(expression);
		assertEquals("abc", expression.getValue());

		expression = parser.parseExpression("T(Boolean).FALSE?'abc':'def'");
		assertEquals("def", expression.getValue());
		assertCanCompile(expression);
		assertEquals("def", expression.getValue());
	}
