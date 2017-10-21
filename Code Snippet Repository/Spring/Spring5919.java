	@Test
	public void testGreaterThanOrEqual() {
		evaluate("3 >= 5", false, Boolean.class);
		evaluate("5 >= 3", true, Boolean.class);
		evaluate("6 >= 6", true, Boolean.class);
		evaluate("3L >= 5L", false, Boolean.class);
		evaluate("5L >= 3L", true, Boolean.class);
		evaluate("5L >= 5L", true, Boolean.class);
		evaluate("3.0d >= 5.0d", false, Boolean.class);
		evaluate("5.0d >= 3.0d", true, Boolean.class);
		evaluate("5.0d >= 5.0d", true, Boolean.class);
		evaluate("new java.math.BigDecimal('5') >= new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') >= new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('5') >= new java.math.BigDecimal('3')", true, Boolean.class);
		evaluate("3 >= new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('3') >= 5", false, Boolean.class);
		evaluate("3L >= new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("3.0d >= new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("3L >= new java.math.BigDecimal('3.1')", false, Boolean.class);
		evaluate("3.0d >= new java.math.BigDecimal('3.1')", false, Boolean.class);
		evaluate("3.0d >= new java.math.BigDecimal('3.0')", true, Boolean.class);
		evaluate("'abc' >= 'def'", false, Boolean.class);
		evaluate("'def' >= 'abc'", true, Boolean.class);
		evaluate("'abc' >= 'abc'", true, Boolean.class);

		evaluate("3 GE 5", false, Boolean.class);
		evaluate("5 gE 3", true, Boolean.class);
		evaluate("6 Ge 6", true, Boolean.class);
		evaluate("3L ge 5L", false, Boolean.class);
		evaluate("5L ge 3L", true, Boolean.class);
		evaluate("5L ge 5L", true, Boolean.class);
		evaluate("3.0d ge 5.0d", false, Boolean.class);
		evaluate("5.0d ge 3.0d", true, Boolean.class);
		evaluate("5.0d ge 5.0d", true, Boolean.class);
		evaluate("new java.math.BigDecimal('5') ge new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') ge new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('5') ge new java.math.BigDecimal('3')", true, Boolean.class);
		evaluate("3 ge new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('3') ge 5", false, Boolean.class);
		evaluate("3L ge new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("3.0d ge new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("3L ge new java.math.BigDecimal('3.1')", false, Boolean.class);
		evaluate("3.0d ge new java.math.BigDecimal('3.1')", false, Boolean.class);
		evaluate("3.0d ge new java.math.BigDecimal('3.0')", true, Boolean.class);
		evaluate("'abc' ge 'def'", false, Boolean.class);
		evaluate("'def' ge 'abc'", true, Boolean.class);
		evaluate("'abc' ge 'abc'", true, Boolean.class);
	}
