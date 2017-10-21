	@Test
	public void props() {
		Properties props = new Properties();
		props.setProperty("x", "1");
		props.setProperty("y", "2");
		props.setProperty("z", "3");
		Expression expression = parser.parseExpression("foo(#props)");
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setVariable("props", props);
		String result = expression.getValue(context, new TestBean(), String.class);
		assertEquals("123", result);
	}
