	@Test
	public void testLessThanOrEqual() {
		evaluate("3 <= 5", true, Boolean.class);
		evaluate("5 <= 3", false, Boolean.class);
		evaluate("6 <= 6", true, Boolean.class);
		evaluate("3L <= 5L", true, Boolean.class);
		evaluate("5L <= 3L", false, Boolean.class);
		evaluate("5L <= 5L", true, Boolean.class);
		evaluate("3.0d <= 5.0d", true, Boolean.class);
		evaluate("5.0d <= 3.0d", false, Boolean.class);
		evaluate("5.0d <= 5.0d", true, Boolean.class);
		evaluate("new java.math.BigDecimal('5') <= new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') <= new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('5') <= new java.math.BigDecimal('3')", false, Boolean.class);
		evaluate("3 <= new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') <= 5", true, Boolean.class);
		evaluate("3L <= new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("3.0d <= new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("3L <= new java.math.BigDecimal('3.1')", true, Boolean.class);
		evaluate("3.0d <= new java.math.BigDecimal('3.1')", true, Boolean.class);
		evaluate("3.0d <= new java.math.BigDecimal('3.0')", true, Boolean.class);
		evaluate("'abc' <= 'def'", true, Boolean.class);
		evaluate("'def' <= 'abc'", false, Boolean.class);
		evaluate("'abc' <= 'abc'", true, Boolean.class);

		evaluate("3 le 5", true, Boolean.class);
		evaluate("5 le 3", false, Boolean.class);
		evaluate("6 Le 6", true, Boolean.class);
		evaluate("3L lE 5L", true, Boolean.class);
		evaluate("5L LE 3L", false, Boolean.class);
		evaluate("5L le 5L", true, Boolean.class);
		evaluate("3.0d LE 5.0d", true, Boolean.class);
		evaluate("5.0d lE 3.0d", false, Boolean.class);
		evaluate("5.0d Le 5.0d", true, Boolean.class);
		evaluate("new java.math.BigDecimal('5') le new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') le new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('5') le new java.math.BigDecimal('3')", false, Boolean.class);
		evaluate("3 le new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') le 5", true, Boolean.class);
		evaluate("3L le new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("3.0d le new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("3L le new java.math.BigDecimal('3.1')", true, Boolean.class);
		evaluate("3.0d le new java.math.BigDecimal('3.1')", true, Boolean.class);
		evaluate("3.0d le new java.math.BigDecimal('3.0')", true, Boolean.class);
		evaluate("'abc' Le 'def'", true, Boolean.class);
		evaluate("'def' LE 'abc'", false, Boolean.class);
		evaluate("'abc' le 'abc'", true, Boolean.class);
	}
