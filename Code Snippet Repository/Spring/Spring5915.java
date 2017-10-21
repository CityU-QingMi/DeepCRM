	@Test
	public void testNotEqual() {
		evaluate("3 != 5", true, Boolean.class);
		evaluate("5 != 3", true, Boolean.class);
		evaluate("6 != 6", false, Boolean.class);
		evaluate("3.0f != 5.0f", true, Boolean.class);
		evaluate("3.0f != 3.0f", false, Boolean.class);
		evaluate("new java.math.BigDecimal('5') != new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('3') != new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('5') != new java.math.BigDecimal('3')", true, Boolean.class);
		evaluate("3 != new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') != 5", true, Boolean.class);
		evaluate("3L != new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("3.0d != new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("3L != new java.math.BigDecimal('3.1')", true, Boolean.class);
		evaluate("3.0d != new java.math.BigDecimal('3.1')", true, Boolean.class);
		evaluate("3.0d != new java.math.BigDecimal('3.0')", false, Boolean.class);
		evaluate("3.0f != 3.0d", false, Boolean.class);
		evaluate("10 != '10'", true, Boolean.class);
		evaluate("'abc' != 'abc'", false, Boolean.class);
		evaluate("'abc' != new java.lang.StringBuilder('abc')", false, Boolean.class);
		evaluate("'abc' != 'def'", true, Boolean.class);
		evaluate("'abc' != null", true, Boolean.class);
		evaluate("new org.springframework.expression.spel.OperatorTests$SubComparable() != new org.springframework.expression.spel.OperatorTests$OtherSubComparable()", false, Boolean.class);

		evaluate("3 ne 5", true, Boolean.class);
		evaluate("5 nE 3", true, Boolean.class);
		evaluate("6 Ne 6", false, Boolean.class);
		evaluate("3.0f NE 5.0f", true, Boolean.class);
		evaluate("3.0f ne 3.0f", false, Boolean.class);
		evaluate("new java.math.BigDecimal('5') ne new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('3') ne new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('5') ne new java.math.BigDecimal('3')", true, Boolean.class);
		evaluate("3 ne new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') ne 5", true, Boolean.class);
		evaluate("3L ne new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("3.0d ne new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("3L ne new java.math.BigDecimal('3.1')", true, Boolean.class);
		evaluate("3.0d ne new java.math.BigDecimal('3.1')", true, Boolean.class);
		evaluate("3.0d ne new java.math.BigDecimal('3.0')", false, Boolean.class);
		evaluate("3.0f ne 3.0d", false, Boolean.class);
		evaluate("10 ne '10'", true, Boolean.class);
		evaluate("'abc' ne 'abc'", false, Boolean.class);
		evaluate("'abc' ne new java.lang.StringBuilder('abc')", false, Boolean.class);
		evaluate("'abc' ne 'def'", true, Boolean.class);
		evaluate("'abc' ne null", true, Boolean.class);
		evaluate("new org.springframework.expression.spel.OperatorTests$SubComparable() ne new org.springframework.expression.spel.OperatorTests$OtherSubComparable()", false, Boolean.class);
	}
