	@Test
	public void testMethodFiltering_SPR6764() {
		SpelExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setRootObject(new TestObject());
		LocalFilter filter = new LocalFilter();
		context.registerMethodFilter(TestObject.class,filter);

		// Filter will be called but not do anything, so first doit() will be invoked
		SpelExpression expr = (SpelExpression) parser.parseExpression("doit(1)");
		String result = expr.getValue(context, String.class);
		assertEquals("1", result);
		assertTrue(filter.filterCalled);

		// Filter will now remove non @Anno annotated methods
		filter.removeIfNotAnnotated = true;
		filter.filterCalled = false;
		expr = (SpelExpression) parser.parseExpression("doit(1)");
		result = expr.getValue(context, String.class);
		assertEquals("double 1.0", result);
		assertTrue(filter.filterCalled);

		// check not called for other types
		filter.filterCalled = false;
		context.setRootObject(new String("abc"));
		expr = (SpelExpression) parser.parseExpression("charAt(0)");
		result = expr.getValue(context, String.class);
		assertEquals("a", result);
		assertFalse(filter.filterCalled);

		// check de-registration works
		filter.filterCalled = false;
		context.registerMethodFilter(TestObject.class,null);//clear filter
		context.setRootObject(new TestObject());
		expr = (SpelExpression) parser.parseExpression("doit(1)");
		result = expr.getValue(context, String.class);
		assertEquals("1", result);
		assertFalse(filter.filterCalled);
	}
