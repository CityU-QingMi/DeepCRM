	@Test
	public void booleanOperators_symbolic_spr9614() {
		SpelExpression expr = new SpelExpressionParser().parseRaw("true");
		assertEquals(Boolean.TRUE, expr.getValue(Boolean.class));
		expr = new SpelExpressionParser().parseRaw("false");
		assertEquals(Boolean.FALSE, expr.getValue(Boolean.class));
		expr = new SpelExpressionParser().parseRaw("false && false");
		assertEquals(Boolean.FALSE, expr.getValue(Boolean.class));
		expr = new SpelExpressionParser().parseRaw("true && (true || false)");
		assertEquals(Boolean.TRUE, expr.getValue(Boolean.class));
		expr = new SpelExpressionParser().parseRaw("true && true || false");
		assertEquals(Boolean.TRUE, expr.getValue(Boolean.class));
		expr = new SpelExpressionParser().parseRaw("!true");
		assertEquals(Boolean.FALSE, expr.getValue(Boolean.class));
		expr = new SpelExpressionParser().parseRaw("!(false || true)");
		assertEquals(Boolean.FALSE, expr.getValue(Boolean.class));
	}
