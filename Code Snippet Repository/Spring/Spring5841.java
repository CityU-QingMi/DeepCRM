	@Test
	public void testScenario_DefiningVariablesThatWillBeAccessibleInExpressions() throws Exception {
		// Create a parser
		SpelExpressionParser parser = new SpelExpressionParser();
		// Use the standard evaluation context
		StandardEvaluationContext ctx = new StandardEvaluationContext();
		ctx.setVariable("favouriteColour","blue");
		List<Integer> primes = new ArrayList<>();
		primes.addAll(Arrays.asList(2,3,5,7,11,13,17));
		ctx.setVariable("primes",primes);

		Expression expr = parser.parseRaw("#favouriteColour");
		Object value = expr.getValue(ctx);
		assertEquals("blue", value);

		expr = parser.parseRaw("#primes.get(1)");
		value = expr.getValue(ctx);
		assertEquals(3, value);

		// all prime numbers > 10 from the list (using selection ?{...})
		expr = parser.parseRaw("#primes.?[#this>10]");
		value = expr.getValue(ctx);
		assertEquals("[11, 13, 17]", value.toString());
	}
