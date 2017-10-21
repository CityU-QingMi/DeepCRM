	@Test
	public void testLiterals() throws Exception {
		ExpressionParser parser = new SpelExpressionParser();

		String helloWorld = (String) parser.parseExpression("'Hello World'").getValue(); // evals to "Hello World"
		assertEquals("Hello World",helloWorld);

		double avogadrosNumber  = (Double) parser.parseExpression("6.0221415E+23").getValue();
		assertEquals(6.0221415E+23, avogadrosNumber, 0);

		int maxValue = (Integer) parser.parseExpression("0x7FFFFFFF").getValue();  // evals to 2147483647
		assertEquals(Integer.MAX_VALUE,maxValue);

		boolean trueValue = (Boolean) parser.parseExpression("true").getValue();
		assertTrue(trueValue);

		Object nullValue = parser.parseExpression("null").getValue();
		assertNull(nullValue);
	}
