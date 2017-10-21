	@Test
	public void testPlus() throws Exception {
		evaluate("7 + 2", "9", Integer.class);
		evaluate("3.0f + 5.0f", 8.0f, Float.class);
		evaluate("3.0d + 5.0d", 8.0d, Double.class);
		evaluate("3 + new java.math.BigDecimal('5')", new BigDecimal("8"), BigDecimal.class);

		evaluate("'ab' + 2", "ab2", String.class);
		evaluate("2 + 'a'", "2a", String.class);
		evaluate("'ab' + null", "abnull", String.class);
		evaluate("null + 'ab'", "nullab", String.class);

		// AST:
		SpelExpression expr = (SpelExpression)parser.parseExpression("+3");
		assertEquals("+3",expr.toStringAST());
		expr = (SpelExpression)parser.parseExpression("2+3");
		assertEquals("(2 + 3)",expr.toStringAST());

		// use as a unary operator
		evaluate("+5d",5d,Double.class);
		evaluate("+5L",5L,Long.class);
		evaluate("+5",5,Integer.class);
		evaluate("+new java.math.BigDecimal('5')", new BigDecimal("5"),BigDecimal.class);
		evaluateAndCheckError("+'abc'",SpelMessage.OPERATOR_NOT_SUPPORTED_BETWEEN_TYPES);

		// string concatenation
		evaluate("'abc'+'def'","abcdef",String.class);

		evaluate("5 + new Integer('37')",42,Integer.class);
	}
