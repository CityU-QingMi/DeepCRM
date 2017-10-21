	@Test
	public void testScenario_AddingYourOwnPropertyResolvers_1() throws Exception {
		// Create a parser
		SpelExpressionParser parser = new SpelExpressionParser();
		// Use the standard evaluation context
		StandardEvaluationContext ctx = new StandardEvaluationContext();

		ctx.addPropertyAccessor(new FruitColourAccessor());
		Expression expr = parser.parseRaw("orange");
		Object value = expr.getValue(ctx);
		assertEquals(Color.orange, value);

		try {
			expr.setValue(ctx, Color.blue);
			fail("Should not be allowed to set oranges to be blue !");
		}
		catch (SpelEvaluationException ee) {
			assertEquals(ee.getMessageCode(), SpelMessage.PROPERTY_OR_FIELD_NOT_WRITABLE_ON_NULL);
		}
	}
