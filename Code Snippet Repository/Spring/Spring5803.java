	public void evaluate(String expression, Object expectedValue, Class<?> expectedClassOfResult, boolean shouldBeWritable) {
		Expression expr = parser.parseExpression(expression);
		if (expr == null) {
			fail("Parser returned null for expression");
		}
		if (DEBUG) {
			SpelUtilities.printAbstractSyntaxTree(System.out, expr);
		}
		Object value = expr.getValue(eContext);
		if (value == null) {
			if (expectedValue == null) {
				return;  // no point doing other checks
			}
			assertEquals("Expression returned null value, but expected '" + expectedValue + "'", expectedValue, null);
		}
		Class<? extends Object> resultType = value.getClass();
		if (expectedValue instanceof String) {
			assertEquals("Did not get expected value for expression '" + expression + "'.", expectedValue,
					AbstractExpressionTests.stringValueOf(value));
		}
		else {
			assertEquals("Did not get expected value for expression '" + expression + "'.", expectedValue, value);
		}
		assertEquals("Type of the result was not as expected.  Expected '" + expectedClassOfResult +
				"' but result was of type '" + resultType + "'", expectedClassOfResult.equals(resultType), true);

		boolean isWritable = expr.isWritable(eContext);
		if (isWritable != shouldBeWritable) {
			if (shouldBeWritable)
				fail("Expected the expression to be writable but it is not");
			else
				fail("Expected the expression to be readonly but it is not");
		}
	}
