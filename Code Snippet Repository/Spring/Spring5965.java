	@Test
	public void functionReferenceVisibility_SPR12359() throws Exception {
		// Confirms visibility of what is being called.
		StandardEvaluationContext context = new StandardEvaluationContext(new Object[] {"1"});
		context.registerFunction("doCompare", SomeCompareMethod.class.getDeclaredMethod(
				"compare", Object.class, Object.class));
		context.setVariable("arg", "2");
		// type nor method are public
		expression = parser.parseExpression("#doCompare([0],#arg)");
		assertEquals("-1", expression.getValue(context, Integer.class).toString());
		assertCantCompile(expression);

		// type not public but method is
		context = new StandardEvaluationContext(new Object[] {"1"});
		context.registerFunction("doCompare", SomeCompareMethod.class.getDeclaredMethod(
				"compare2", Object.class, Object.class));
		context.setVariable("arg", "2");
		expression = parser.parseExpression("#doCompare([0],#arg)");
		assertEquals("-1", expression.getValue(context, Integer.class).toString());
		assertCantCompile(expression);
	}
