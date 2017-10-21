	@Test
	public void compilingMethodReference() throws Exception {
		long interpretedTotal = 0, compiledTotal = 0;
		long stime,etime;
		String interpretedResult = null,compiledResult = null;

		HW testdata = new HW();
		Expression expression = parser.parseExpression("hello()");

		// warmup
		for (int i = 0; i < count; i++) {
			interpretedResult = expression.getValue(testdata, String.class);
		}

		log("timing interpreted: ");
		for (int i = 0; i < iterations; i++) {
			stime = System.currentTimeMillis();
			for (int j = 0; j < count; j++) {
				interpretedResult = expression.getValue(testdata, String.class);
			}
			etime = System.currentTimeMillis();
			long interpretedSpeed = (etime - stime);
			interpretedTotal += interpretedSpeed;
			log(interpretedSpeed + "ms ");
		}
		logln();

		compile(expression);

		log("timing compiled: ");
		expression.getValue(testdata, String.class);
		for (int i = 0; i < iterations; i++) {
			stime = System.currentTimeMillis();
			for (int j = 0; j < count; j++) {
				compiledResult = expression.getValue(testdata, String.class);
			}
			etime = System.currentTimeMillis();
			long compiledSpeed = (etime - stime);
			compiledTotal += compiledSpeed;
			log(compiledSpeed + "ms ");
		}
		logln();

		assertEquals(interpretedResult,compiledResult);
		reportPerformance("method reference", interpretedTotal, compiledTotal);
		if (compiledTotal>=interpretedTotal) {
			fail("Compiled version is slower than interpreted!");
		}
	}
