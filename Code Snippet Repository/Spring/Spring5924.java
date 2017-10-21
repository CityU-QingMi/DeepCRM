	@Test
	public void testPerformanceOfPropertyAccess() throws Exception {
		Assume.group(TestGroup.PERFORMANCE);

		long starttime = 0;
		long endtime = 0;

		// warmup
		for (int i = 0; i < ITERATIONS; i++) {
			Expression expr = parser.parseExpression("placeOfBirth.city");
			if (expr == null) {
				fail("Parser returned null for expression");
			}
			expr.getValue(eContext);
		}

		starttime = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			Expression expr = parser.parseExpression("placeOfBirth.city");
			if (expr == null) {
				fail("Parser returned null for expression");
			}
			expr.getValue(eContext);
		}
		endtime = System.currentTimeMillis();
		long freshParseTime = endtime - starttime;
		if (DEBUG) {
			System.out.println("PropertyAccess: Time for parsing and evaluation x 10000: "+freshParseTime+"ms");
		}

		Expression expr = parser.parseExpression("placeOfBirth.city");
		if (expr == null) {
			fail("Parser returned null for expression");
		}
		starttime = System.currentTimeMillis();
		for (int i = 0; i < ITERATIONS; i++) {
			expr.getValue(eContext);
		}
		endtime = System.currentTimeMillis();
		long reuseTime = endtime - starttime;
		if (DEBUG) {
			System.out.println("PropertyAccess: Time for just evaluation x 10000: "+reuseTime+"ms");
		}
		if (reuseTime > freshParseTime) {
			System.out.println("Fresh parse every time, ITERATIONS iterations = " + freshParseTime + "ms");
			System.out.println("Reuse SpelExpression, ITERATIONS iterations = " + reuseTime + "ms");
			fail("Should have been quicker to reuse!");
		}
	}
