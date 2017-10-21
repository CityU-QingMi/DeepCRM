	@Test
	public void realLiteral() throws Exception {
		expression = parser.parseExpression("3.4d");
		double resultI = expression.getValue(new TestClass1(), Double.TYPE);
		assertCanCompile(expression);
		double resultC = expression.getValue(new TestClass1(), Double.TYPE);
		assertEquals(3.4d, resultI, 0.1d);
		assertEquals(3.4d, resultC, 0.1d);
		assertEquals(3.4d, expression.getValue());
	}
