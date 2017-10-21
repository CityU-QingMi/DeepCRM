	@Test
	public void SPR5899() throws Exception {
		StandardEvaluationContext eContext = new StandardEvaluationContext(new Spr5899Class());
		Expression expr = new SpelExpressionParser().parseRaw("tryToInvokeWithNull(12)");
		assertEquals(12, expr.getValue(eContext));
		expr = new SpelExpressionParser().parseRaw("tryToInvokeWithNull(null)");
		assertEquals(null, expr.getValue(eContext));
		try {
			expr = new SpelExpressionParser().parseRaw("tryToInvokeWithNull2(null)");
			expr.getValue();
			fail("Should have failed to find a method to which it could pass null");
		}
		catch (EvaluationException see) {
			// success
		}
		eContext.setTypeLocator(new MyTypeLocator());

		// varargs
		expr = new SpelExpressionParser().parseRaw("tryToInvokeWithNull3(null,'a','b')");
		assertEquals("ab", expr.getValue(eContext));

		// varargs 2 - null is packed into the varargs
		expr = new SpelExpressionParser().parseRaw("tryToInvokeWithNull3(12,'a',null,'c')");
		assertEquals("anullc", expr.getValue(eContext));

		// check we can find the ctor ok
		expr = new SpelExpressionParser().parseRaw("new Spr5899Class().toString()");
		assertEquals("instance", expr.getValue(eContext));

		expr = new SpelExpressionParser().parseRaw("new Spr5899Class(null).toString()");
		assertEquals("instance", expr.getValue(eContext));

		// ctor varargs
		expr = new SpelExpressionParser().parseRaw("new Spr5899Class(null,'a','b').toString()");
		assertEquals("instance", expr.getValue(eContext));

		// ctor varargs 2
		expr = new SpelExpressionParser().parseRaw("new Spr5899Class(null,'a', null, 'b').toString()");
		assertEquals("instance", expr.getValue(eContext));
	}
