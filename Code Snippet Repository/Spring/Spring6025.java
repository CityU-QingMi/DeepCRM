	@Test
	public void testFunctions() throws Exception {
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();

		context.registerFunction("reverseString", StringUtils.class.getDeclaredMethod(
				"reverseString", new Class[] { String.class }));

		String helloWorldReversed = parser.parseExpression("#reverseString('hello world')").getValue(context, String.class);
		assertEquals("dlrow olleh",helloWorldReversed);
	}
