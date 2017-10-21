	@Test
	public void intLiteral() throws Exception {
		expression = parser.parseExpression("42");
		int resultI = expression.getValue(new TestClass1(), Integer.TYPE);
		assertCanCompile(expression);
		int resultC = expression.getValue(new TestClass1(), Integer.TYPE);
		assertEquals(42, resultI);
		assertEquals(42, resultC);

		expression = parser.parseExpression("T(Integer).valueOf(42)");
		expression.getValue(Integer.class);
		assertCanCompile(expression);
		assertEquals(new Integer(42), expression.getValue(Integer.class));

		// Code gen is different for -1 .. 6 because there are bytecode instructions specifically for those values

		// Not an int literal but an opminus with one operand:
		// expression = parser.parseExpression("-1");
		// assertCanCompile(expression);
		// assertEquals(-1, expression.getValue());
		expression = parser.parseExpression("0");
		assertCanCompile(expression);
		assertEquals(0, expression.getValue());
		expression = parser.parseExpression("2");
		assertCanCompile(expression);
		assertEquals(2, expression.getValue());
		expression = parser.parseExpression("7");
		assertCanCompile(expression);
		assertEquals(7, expression.getValue());
	}
