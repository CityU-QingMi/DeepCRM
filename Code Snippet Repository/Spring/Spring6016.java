	@Test
	public void compilingPropertyReferenceNestedMixedFieldGetter() throws Exception {
		long interpretedTotal = 0, compiledTotal = 0, stime, etime;
		String interpretedResult = null, compiledResult = null;

		TestClass2 testdata = new TestClass2();
		Expression expression = parser.parseExpression("foo.baz.boo");

		// warmup
		for (int i = 0; i < count; i++) {
			expression.getValue(testdata, String.class);
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
		reportPerformance("nested property reference (mixed field/getter)",interpretedTotal, compiledTotal);
	}
