	@Test
	public void testMethodThrowingException_SPR6941_2() {
		// Test method on inventor: throwException()
		// On 1 it will throw an IllegalArgumentException
		// On 2 it will throw a RuntimeException
		// On 3 it will exit normally
		// In each case it increments the Inventor field 'counter' when invoked

		SpelExpressionParser parser = new SpelExpressionParser();
		Expression expr = parser.parseExpression("throwException(#bar)");

		eContext.setVariable("bar", 4);
		try {
			expr.getValue(eContext);
			fail();
		}
		catch (ExpressionInvocationTargetException ex) {
			Throwable cause = ex.getCause();
			assertEquals("org.springframework.expression.spel.testresources.Inventor$TestException",
					cause.getClass().getName());
		}
	}
