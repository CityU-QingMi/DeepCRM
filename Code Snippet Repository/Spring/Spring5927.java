	public void testAddingSpecificPropertyAccessor() throws Exception {
		SpelExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext ctx = new StandardEvaluationContext();

		// Even though this property accessor is added after the reflection one, it specifically
		// names the String class as the type it is interested in so is chosen in preference to
		// any 'default' ones
		ctx.addPropertyAccessor(new StringyPropertyAccessor());
		Expression expr = parser.parseRaw("new String('hello').flibbles");
		Integer i = expr.getValue(ctx, Integer.class);
		assertEquals((int) i, 7);

		// The reflection one will be used for other properties...
		expr = parser.parseRaw("new String('hello').CASE_INSENSITIVE_ORDER");
		Object o = expr.getValue(ctx);
		assertNotNull(o);

		expr = parser.parseRaw("new String('hello').flibbles");
		expr.setValue(ctx, 99);
		i = expr.getValue(ctx, Integer.class);
		assertEquals((int) i, 99);

		// Cannot set it to a string value
		try {
			expr.setValue(ctx, "not allowed");
			fail("Should not have been allowed");
		}
		catch (EvaluationException ex) {
			// success - message will be: EL1063E:(pos 20): A problem occurred whilst attempting to set the property
			// 'flibbles': 'Cannot set flibbles to an object of type 'class java.lang.String''
			// System.out.println(e.getMessage());
		}
	}
