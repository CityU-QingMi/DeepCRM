	@Test
	public void booleanOperators() {
		SpelExpression expr = new SpelExpressionParser().parseRaw("true");
		assertEquals(Boolean.TRUE, expr.getValue(Boolean.class));
		expr = new SpelExpressionParser().parseRaw("false");
		assertEquals(Boolean.FALSE, expr.getValue(Boolean.class));
		expr = new SpelExpressionParser().parseRaw("false and false");
		assertEquals(Boolean.FALSE, expr.getValue(Boolean.class));
		expr = new SpelExpressionParser().parseRaw("true and (true or false)");
		assertEquals(Boolean.TRUE, expr.getValue(Boolean.class));
		expr = new SpelExpressionParser().parseRaw("true and true or false");
		assertEquals(Boolean.TRUE, expr.getValue(Boolean.class));
		expr = new SpelExpressionParser().parseRaw("!true");
		assertEquals(Boolean.FALSE, expr.getValue(Boolean.class));
		expr = new SpelExpressionParser().parseRaw("!(false or true)");
		assertEquals(Boolean.FALSE, expr.getValue(Boolean.class));
	}
