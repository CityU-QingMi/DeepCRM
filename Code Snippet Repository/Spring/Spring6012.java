	@Test
	public void stringConcatenation() throws Exception {
		expression = parser.parseExpression("'hello' + getWorld() + ' spring'");
		Greeter g = new Greeter();
		Object o = expression.getValue(g);
		assertEquals("helloworld spring", o);

		System.out.println("Performance check for SpEL expression: 'hello' + getWorld() + ' spring'");
		long stime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			o = expression.getValue(g);
		}
		System.out.println("One million iterations: " + (System.currentTimeMillis()-stime) + "ms");
		stime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			o = expression.getValue(g);
		}
		System.out.println("One million iterations: " + (System.currentTimeMillis()-stime) + "ms");
		stime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			o = expression.getValue(g);
		}
		System.out.println("One million iterations: " + (System.currentTimeMillis()-stime) + "ms");
		compile(expression);
		System.out.println("Now compiled:");
		o = expression.getValue(g);
		assertEquals("helloworld spring", o);

		stime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			o = expression.getValue(g);
		}
		System.out.println("One million iterations: " + (System.currentTimeMillis()-stime) + "ms");
		stime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			o = expression.getValue(g);
		}
		System.out.println("One million iterations: " + (System.currentTimeMillis()-stime) + "ms");
		stime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			o = expression.getValue(g);
		}
		System.out.println("One million iterations: " + (System.currentTimeMillis()-stime) + "ms");
	}
