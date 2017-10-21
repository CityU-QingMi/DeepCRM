	public void evaluate(String expression, Object expectedValue, Class<?> expectedResultType) {
		Expression expr = parser.parseExpression(expression);
		if (expr == null) {
			fail("Parser returned null for expression");
		}
		if (DEBUG) {
			SpelUtilities.printAbstractSyntaxTree(System.out, expr);
		}

		Object value = expr.getValue(eContext);

		// Check the return value
		if (value == null) {
			if (expectedValue == null) {
				return;  // no point doing other checks
			}
			assertEquals("Expression returned null value, but expected '" + expectedValue + "'", expectedValue, null);
		}

		Class<?> resultType = value.getClass();
		assertEquals("Type of the actual result was not as expected.  Expected '" + expectedResultType +
				"' but result was of type '" + resultType + "'", expectedResultType, resultType);

		if (expectedValue instanceof String) {
			assertEquals("Did not get expected value for expression '" + expression + "'.", expectedValue,
					AbstractExpressionTests.stringValueOf(value));
		}
		else {
			assertEquals("Did not get expected value for expression '" + expression + "'.", expectedValue, value);
		}
	}
