	@Test
	public void caseInsensitiveNullLiterals() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp;

		exp = parser.parseExpression("null");
		assertNull(exp.getValue());

		exp = parser.parseExpression("NULL");
		assertNull(exp.getValue());

		exp = parser.parseExpression("NuLl");
		assertNull(exp.getValue());
	}
