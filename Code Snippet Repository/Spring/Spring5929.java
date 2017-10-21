	@Test
	public void shouldAlwaysUsePropertyAccessorFromEvaluationContext() {
		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expression = parser.parseExpression("name");

		StandardEvaluationContext context = new StandardEvaluationContext();
		context.addPropertyAccessor(new ConfigurablePropertyAccessor(Collections.singletonMap("name", "Ollie")));
		assertEquals("Ollie", expression.getValue(context));

		context = new StandardEvaluationContext();
		context.addPropertyAccessor(new ConfigurablePropertyAccessor(Collections.singletonMap("name", "Jens")));
		assertEquals("Jens", expression.getValue(context));
	}
