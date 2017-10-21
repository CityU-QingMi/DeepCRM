	@Test
	public void compiledExpressionShouldWorkWhenUsingCustomFunctionWithVarargs() throws Exception {
		StandardEvaluationContext context = null;

		// Here the target method takes Object... and we are passing a string
		expression = parser.parseExpression("#doFormat('hey %s', 'there')");
		context = new StandardEvaluationContext();
		context.registerFunction("doFormat",
				DelegatingStringFormat.class.getDeclaredMethod("format", String.class, Object[].class));
		((SpelExpression) expression).setEvaluationContext(context);

		assertEquals("hey there", expression.getValue(String.class));
		assertTrue(((SpelNodeImpl) ((SpelExpression) expression).getAST()).isCompilable());
		assertCanCompile(expression);
		assertEquals("hey there", expression.getValue(String.class));

		expression = parser.parseExpression("#doFormat([0], 'there')");
		context = new StandardEvaluationContext(new Object[] {"hey %s"});
		context.registerFunction("doFormat",
				DelegatingStringFormat.class.getDeclaredMethod("format", String.class, Object[].class));
		((SpelExpression) expression).setEvaluationContext(context);

		assertEquals("hey there", expression.getValue(String.class));
		assertTrue(((SpelNodeImpl) ((SpelExpression) expression).getAST()).isCompilable());
		assertCanCompile(expression);
		assertEquals("hey there", expression.getValue(String.class));

		expression = parser.parseExpression("#doFormat([0], #arg)");
		context = new StandardEvaluationContext(new Object[] {"hey %s"});
		context.registerFunction("doFormat",
				DelegatingStringFormat.class.getDeclaredMethod("format", String.class, Object[].class));
		context.setVariable("arg", "there");
		((SpelExpression) expression).setEvaluationContext(context);

		assertEquals("hey there", expression.getValue(String.class));
		assertTrue(((SpelNodeImpl) ((SpelExpression) expression).getAST()).isCompilable());
		assertCanCompile(expression);
		assertEquals("hey there", expression.getValue(String.class));
	}
