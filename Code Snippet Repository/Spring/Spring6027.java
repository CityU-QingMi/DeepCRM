	@Test
	public void testEqualityCheck() throws Exception {
		ExpressionParser parser = new SpelExpressionParser();

		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setRootObject(tesla);

		Expression exp = parser.parseExpression("name == 'Nikola Tesla'");
		boolean isEqual = exp.getValue(context, Boolean.class);  // evaluates to true
		assertTrue(isEqual);
	}
