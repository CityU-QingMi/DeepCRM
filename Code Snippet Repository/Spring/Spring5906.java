	@Test
	public void testModulus() {
		evaluate("3%2",1,Integer.class);
		evaluate("3L%2L",1L,Long.class);
		evaluate("3.0f%2.0f",1f,Float.class);
		evaluate("5.0d % 3.1d", 1.9d, Double.class);
		evaluate("new java.math.BigDecimal('5') % new java.math.BigDecimal('3')", new BigDecimal("2"), BigDecimal.class);
		evaluate("new java.math.BigDecimal('5') % 3", new BigDecimal("2"), BigDecimal.class);
		evaluateAndCheckError("'abc'%'def'",SpelMessage.OPERATOR_NOT_SUPPORTED_BETWEEN_TYPES);
	}
