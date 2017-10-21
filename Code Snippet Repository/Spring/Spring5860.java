	@Test
	public void testConvert() {
		Foo root = new Foo("bar");
		Collection<String> foos = Collections.singletonList("baz");

		StandardEvaluationContext context = new StandardEvaluationContext(root);

		// property access
		Expression expression = parser.parseExpression("foos");
		expression.setValue(context, foos);
		Foo baz = root.getFoos().iterator().next();
		assertEquals("baz", baz.value);

		// method call
		expression = parser.parseExpression("setFoos(#foos)");
		context.setVariable("foos", foos);
		expression.getValue(context);
		baz = root.getFoos().iterator().next();
		assertEquals("baz", baz.value);

		// method call with result from method call
		expression = parser.parseExpression("setFoos(getFoosAsStrings())");
		expression.getValue(context);
		baz = root.getFoos().iterator().next();
		assertEquals("baz", baz.value);

		// method call with result from method call
		expression = parser.parseExpression("setFoos(getFoosAsObjects())");
		expression.getValue(context);
		baz = root.getFoos().iterator().next();
		assertEquals("baz", baz.value);
	}
