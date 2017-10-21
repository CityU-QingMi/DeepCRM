	@Test
	public void whitespace() {
		SpelExpressionParser parser = new SpelExpressionParser();
		SpelExpression expr = parser.parseRaw("2      +    3");
		assertEquals(5, expr.getValue());
		expr = parser.parseRaw("2	+	3");
		assertEquals(5, expr.getValue());
		expr = parser.parseRaw("2\n+\t3");
		assertEquals(5, expr.getValue());
		expr = parser.parseRaw("2\r\n+\t3");
		assertEquals(5, expr.getValue());
	}
