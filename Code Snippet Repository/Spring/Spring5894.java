	@Test
	public void testMethodThrowingException_SPR6941() {
		// Test method on inventor: throwException()
		// On 1 it will throw an IllegalArgumentException
		// On 2 it will throw a RuntimeException
		// On 3 it will exit normally
		// In each case it increments the Inventor field 'counter' when invoked

		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expr = parser.parseExpression("throwException(#bar)");

		eContext.setVariable("bar", 2);
		try {
			expr.getValue(eContext);
			fail();
		}
		catch (Exception ex) {
			if (ex instanceof SpelEvaluationException) {
				fail("Should not be a SpelEvaluationException: " + ex);
			}
			// normal
		}
	}
