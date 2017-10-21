	@Test
	public void inlineNestedLists() throws Exception {
		expression = parser.parseExpression("{'abcde',{'ijklm','nopqr'}}[1][0].substring({1,3,4}[0],{1,3,4}[1])");
		Object o = expression.getValue();
		assertEquals("jk",o);
		System.out.println("Performance check for SpEL expression: '{'abcde','ijklm'}[0].substring({1,3,4}[0],{1,3,4}[1])'");
		long stime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			o = expression.getValue();
		}
		System.out.println("One million iterations: " + (System.currentTimeMillis()-stime) + "ms");
		stime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			o = expression.getValue();
		}
		System.out.println("One million iterations: " + (System.currentTimeMillis()-stime) + "ms");
		stime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			o = expression.getValue();
		}
		System.out.println("One million iterations: " + (System.currentTimeMillis()-stime) + "ms");
		compile(expression);
		System.out.println("Now compiled:");
		o = expression.getValue();
		assertEquals("jk", o);

		stime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			o = expression.getValue();
		}
		System.out.println("One million iterations: " + (System.currentTimeMillis()-stime) + "ms");
		stime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			o = expression.getValue();
		}
		System.out.println("One million iterations: " + (System.currentTimeMillis()-stime) + "ms");
		stime = System.currentTimeMillis();
		for (int i = 0; i < 1000000; i++) {
			o = expression.getValue();
		}
		System.out.println("One million iterations: " + (System.currentTimeMillis()-stime) + "ms");
	}
