	@Test
	public void failsWhenSettingContextForExpression_SPR12326() {
		SpelExpressionParser parser = new SpelExpressionParser(
				new SpelParserConfiguration(SpelCompilerMode.IMMEDIATE, getClass().getClassLoader()));
		Person3 person = new Person3("foo", 1);
		SpelExpression expression = parser.parseRaw("#it?.age?.equals([0])");
		StandardEvaluationContext context = new StandardEvaluationContext(new Object[] {1});
		context.setVariable("it", person);
		expression.setEvaluationContext(context);
		assertTrue(expression.getValue(Boolean.class));
		assertTrue(expression.getValue(Boolean.class));
		assertCanCompile(expression);
		assertTrue(expression.getValue(Boolean.class));
	}
