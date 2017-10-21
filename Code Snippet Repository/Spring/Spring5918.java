	@Test
	public void testGreaterThan() {
		evaluate("3 > 5", false, Boolean.class);
		evaluate("5 > 3", true, Boolean.class);
		evaluate("3L > 5L", false, Boolean.class);
		evaluate("5L > 3L", true, Boolean.class);
		evaluate("3.0d > 5.0d", false, Boolean.class);
		evaluate("5.0d > 3.0d", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') > new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('5') > new java.math.BigDecimal('3')", true, Boolean.class);
		evaluate("3 > new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('3') > 5", false, Boolean.class);
		evaluate("3L > new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("3.0d > new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("3L > new java.math.BigDecimal('3.1')", false, Boolean.class);
		evaluate("3.0d > new java.math.BigDecimal('3.1')", false, Boolean.class);
		evaluate("3.0d > new java.math.BigDecimal('3.0')", false, Boolean.class);
		evaluate("'abc' > 'def'", false, Boolean.class);
		evaluate("'abc' > new java.lang.StringBuilder('def')", false, Boolean.class);
		evaluate("'def' > 'abc'", true, Boolean.class);

		evaluate("3 gt 5", false, Boolean.class);
		evaluate("5 gt 3", true, Boolean.class);
		evaluate("3L gt 5L", false, Boolean.class);
		evaluate("5L gt 3L", true, Boolean.class);
		evaluate("3.0d gt 5.0d", false, Boolean.class);
		evaluate("5.0d gT 3.0d", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') gt new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('5') gt new java.math.BigDecimal('3')", true, Boolean.class);
		evaluate("3 gt new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('3') gt 5", false, Boolean.class);
		evaluate("3L gt new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("3.0d gt new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("3L gt new java.math.BigDecimal('3.1')", false, Boolean.class);
		evaluate("3.0d gt new java.math.BigDecimal('3.1')", false, Boolean.class);
		evaluate("3.0d gt new java.math.BigDecimal('3.0')", false, Boolean.class);
		evaluate("'abc' Gt 'def'", false, Boolean.class);
		evaluate("'abc' gt new java.lang.StringBuilder('def')", false, Boolean.class);
		evaluate("'def' GT 'abc'", true, Boolean.class);
	}
