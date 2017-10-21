	@Test
	public void floatLiteral() throws Exception {
		expression = parser.parseExpression("3.4f");
		float resultI = expression.getValue(new TestClass1(), Float.TYPE);
		assertCanCompile(expression);
		float resultC = expression.getValue(new TestClass1(), Float.TYPE);
		assertEquals(3.4f, resultI, 0.1f);
		assertEquals(3.4f, resultC, 0.1f);

		assertEquals(3.4f, expression.getValue());
	}
