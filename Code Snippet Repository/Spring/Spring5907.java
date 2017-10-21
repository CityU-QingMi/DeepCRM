	@Test
	public void testDivide() {
		evaluate("3.0f / 5.0f", 0.6f, Float.class);
		evaluate("4L/2L",2L,Long.class);
		evaluate("3.0f div 5.0f", 0.6f, Float.class);
		evaluate("4L DIV 2L",2L,Long.class);
		evaluate("new java.math.BigDecimal('3') / 5", new BigDecimal("1"), BigDecimal.class);
		evaluate("new java.math.BigDecimal('3.0') / 5", new BigDecimal("0.6"), BigDecimal.class);
		evaluate("new java.math.BigDecimal('3.00') / 5", new BigDecimal("0.60"), BigDecimal.class);
		evaluate("new java.math.BigDecimal('3.00') / new java.math.BigDecimal('5.0000')", new BigDecimal("0.6000"), BigDecimal.class);
		evaluateAndCheckError("'abc'/'def'",SpelMessage.OPERATOR_NOT_SUPPORTED_BETWEEN_TYPES);
	}
