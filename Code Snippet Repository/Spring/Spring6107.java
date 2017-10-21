	@Test
	public void theMostBasic() {
		SpelExpressionParser parser = new SpelExpressionParser();
		SpelExpression expr = parser.parseRaw("2");
		assertNotNull(expr);
		assertNotNull(expr.getAST());
		assertEquals(2, expr.getValue());
		assertEquals(Integer.class, expr.getValueType());
		assertEquals(2, expr.getAST().getValue(null));
	}
