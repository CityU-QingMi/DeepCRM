	@Test
	public void variantGetter() throws Exception {
		Payload2Holder holder = new Payload2Holder();
		StandardEvaluationContext ctx = new StandardEvaluationContext();
		ctx.addPropertyAccessor(new MyAccessor());
		expression = parser.parseExpression("payload2.var1");
		Object v = expression.getValue(ctx,holder);
		assertEquals("abc", v);

		//	// time it interpreted
		//	long stime = System.currentTimeMillis();
		//	for (int i = 0; i < 100000; i++) {
		//		v = expression.getValue(ctx,holder);
		//	}
		//	System.out.println((System.currentTimeMillis() - stime));

		assertCanCompile(expression);
		v = expression.getValue(ctx,holder);
		assertEquals("abc", v);

		//	// time it compiled
		//	stime = System.currentTimeMillis();
		//	for (int i = 0; i < 100000; i++) {
		//		v = expression.getValue(ctx,holder);
		//	}
		//	System.out.println((System.currentTimeMillis() - stime));
	}
