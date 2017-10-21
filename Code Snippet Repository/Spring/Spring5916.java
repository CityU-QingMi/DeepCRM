	@Test
	public void testLessThan() {
		evaluate("5 < 5", false, Boolean.class);
		evaluate("3 < 5", true, Boolean.class);
		evaluate("5 < 3", false, Boolean.class);
		evaluate("3L < 5L", true, Boolean.class);
		evaluate("5L < 3L", false, Boolean.class);
		evaluate("3.0d < 5.0d", true, Boolean.class);
		evaluate("5.0d < 3.0d", false, Boolean.class);
		evaluate("new java.math.BigDecimal('3') < new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('5') < new java.math.BigDecimal('3')", false, Boolean.class);
		evaluate("3 < new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') < 5", true, Boolean.class);
		evaluate("3L < new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("3.0d < new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("3L < new java.math.BigDecimal('3.1')", true, Boolean.class);
		evaluate("3.0d < new java.math.BigDecimal('3.1')", true, Boolean.class);
		evaluate("3.0d < new java.math.BigDecimal('3.0')", false, Boolean.class);
		evaluate("'abc' < 'def'", true, Boolean.class);
		evaluate("'abc' < new java.lang.StringBuilder('def')", true, Boolean.class);
		evaluate("'def' < 'abc'", false, Boolean.class);

		evaluate("3 lt 5", true, Boolean.class);
		evaluate("5 lt 3", false, Boolean.class);
		evaluate("3L lt 5L", true, Boolean.class);
		evaluate("5L lt 3L", false, Boolean.class);
		evaluate("3.0d lT 5.0d", true, Boolean.class);
		evaluate("5.0d Lt 3.0d", false, Boolean.class);
		evaluate("new java.math.BigDecimal('3') lt new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('5') lt new java.math.BigDecimal('3')", false, Boolean.class);
		evaluate("3 lt new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') lt 5", true, Boolean.class);
		evaluate("3L lt new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("3.0d lt new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("3L lt new java.math.BigDecimal('3.1')", true, Boolean.class);
		evaluate("3.0d lt new java.math.BigDecimal('3.1')", true, Boolean.class);
		evaluate("3.0d lt new java.math.BigDecimal('3.0')", false, Boolean.class);
		evaluate("'abc' LT 'def'", true, Boolean.class);
		evaluate("'abc' lt new java.lang.StringBuilder('def')", true, Boolean.class);
		evaluate("'def' lt 'abc'", false, Boolean.class);
	}
