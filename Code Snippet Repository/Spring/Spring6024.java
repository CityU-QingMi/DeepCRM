	@SuppressWarnings("")
	@Test
	public void testSpecialVariables() throws Exception {
		// create an array of integers
		List<Integer> primes = new ArrayList<>();
		primes.addAll(Arrays.asList(2,3,5,7,11,13,17));

		// create parser and set variable 'primes' as the array of integers
		ExpressionParser parser = new SpelExpressionParser();
		StandardEvaluationContext context = new StandardEvaluationContext();
		context.setVariable("primes",primes);

		// all prime numbers > 10 from the list (using selection ?{...})
		List<Integer> primesGreaterThanTen = (List<Integer>) parser.parseExpression("#primes.?[#this>10]").getValue(context);
		assertEquals("[11, 13, 17]",primesGreaterThanTen.toString());
	}
