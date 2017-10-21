	@Test
	public void functionReferenceVarargs() throws Exception {
		EvaluationContext ctx = new StandardEvaluationContext();
		Method m = getClass().getDeclaredMethod("join", String[].class);
		ctx.setVariable("join", m);
		expression = parser.parseExpression("#join('a','b','c')");
		assertEquals("abc", expression.getValue(ctx));
		assertCanCompile(expression);
		assertEquals("abc", expression.getValue(ctx));
	}
