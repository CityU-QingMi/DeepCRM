	@Test
	public void testScenario_AddingYourOwnPropertyResolvers_2() throws Exception {
		// Create a parser
		SpelExpressionParser parser = new SpelExpressionParser();
		// Use the standard evaluation context
		StandardEvaluationContext ctx = new StandardEvaluationContext();

		ctx.addPropertyAccessor(new VegetableColourAccessor());
		Expression expr = parser.parseRaw("pea");
		Object value = expr.getValue(ctx);
		assertEquals(Color.green, value);

		try {
			expr.setValue(ctx, Color.blue);
			fail("Should not be allowed to set peas to be blue !");
		}
		catch (SpelEvaluationException ee) {
			assertEquals(ee.getMessageCode(), SpelMessage.PROPERTY_OR_FIELD_NOT_WRITABLE_ON_NULL);
		}
	}
