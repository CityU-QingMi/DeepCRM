	@Test
	public void testScenario_UsingADifferentRootContextObject() throws Exception {
		// Create a parser
		SpelExpressionParser parser = new SpelExpressionParser();
		// Use the standard evaluation context
		StandardEvaluationContext ctx = new StandardEvaluationContext();

		TestClass tc = new TestClass();
		tc.setProperty(42);
		tc.str = "wibble";
		ctx.setRootObject(tc);

		// read it, set it, read it again
		Expression expr = parser.parseRaw("str");
		Object value = expr.getValue(ctx);
		assertEquals("wibble", value);
		expr = parser.parseRaw("str");
		expr.setValue(ctx, "wobble");
		expr = parser.parseRaw("str");
		value = expr.getValue(ctx);
		assertEquals("wobble", value);
		// or using assignment within the expression
		expr = parser.parseRaw("str='wabble'");
		value = expr.getValue(ctx);
		expr = parser.parseRaw("str");
		value = expr.getValue(ctx);
		assertEquals("wabble", value);

		// private property will be accessed through getter()
		expr = parser.parseRaw("property");
		value = expr.getValue(ctx);
		assertEquals(42, value);

		// ... and set through setter
		expr = parser.parseRaw("property=4");
		value = expr.getValue(ctx);
		expr = parser.parseRaw("property");
		value = expr.getValue(ctx);
		assertEquals(4,value);
	}
