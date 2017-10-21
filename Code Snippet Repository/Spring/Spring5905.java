	@Test
	public void testMinus() throws Exception {
		evaluate("'c' - 2", "a", String.class);
		evaluate("3.0f - 5.0f", -2.0f, Float.class);
		evaluateAndCheckError("'ab' - 2", SpelMessage.OPERATOR_NOT_SUPPORTED_BETWEEN_TYPES);
		evaluateAndCheckError("2-'ab'", SpelMessage.OPERATOR_NOT_SUPPORTED_BETWEEN_TYPES);
		SpelExpression expr = (SpelExpression)parser.parseExpression("-3");
		assertEquals("-3", expr.toStringAST());
		expr = (SpelExpression)parser.parseExpression("2-3");
		assertEquals("(2 - 3)", expr.toStringAST());

		evaluate("-5d",-5d,Double.class);
		evaluate("-5L",-5L,Long.class);
		evaluate("-5", -5, Integer.class);
		evaluate("-new java.math.BigDecimal('5')", new BigDecimal("-5"),BigDecimal.class);
		evaluateAndCheckError("-'abc'", SpelMessage.OPERATOR_NOT_SUPPORTED_BETWEEN_TYPES);
	}
