	@Test
	public void opNe_SPR14863() throws Exception {
		SpelParserConfiguration configuration =
				new SpelParserConfiguration(SpelCompilerMode.MIXED, ClassLoader.getSystemClassLoader());
		SpelExpressionParser parser = new SpelExpressionParser(configuration);
		Expression expression = parser.parseExpression("data['my-key'] != 'my-value'");

		Map<String, String> data = new HashMap<>();
		data.put("my-key", new String("my-value"));
		StandardEvaluationContext context = new StandardEvaluationContext(new MyContext(data));
		assertFalse(expression.getValue(context, Boolean.class));
		assertCanCompile(expression);
		((SpelExpression) expression).compileExpression();
		assertFalse(expression.getValue(context, Boolean.class));

		List<String> ls = new ArrayList<String>();
		ls.add(new String("foo"));
		context = new StandardEvaluationContext(ls);
		expression = parse("get(0) != 'foo'");
		assertFalse(expression.getValue(context, Boolean.class));
		assertCanCompile(expression);
		assertFalse(expression.getValue(context, Boolean.class));

		ls.remove(0);
		ls.add("goo");
		assertTrue(expression.getValue(context, Boolean.class));
	}
