	@Test
	public void SPR9486_floatFunctionResolver() throws Exception {
		Number expectedResult = Math.abs(-10.2f);
		ExpressionParser parser = new SpelExpressionParser();
		SPR9486_FunctionsClass testObject = new SPR9486_FunctionsClass();

		StandardEvaluationContext context = new StandardEvaluationContext();
		Expression expression = parser.parseExpression("abs(-10.2f)");
		Number result = expression.getValue(context, testObject, Number.class);
		assertEquals(expectedResult, result);
	}
