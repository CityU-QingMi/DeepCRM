	@Test
	public void testEqual() {
		evaluate("3 == 5", false, Boolean.class);
		evaluate("5 == 3", false, Boolean.class);
		evaluate("6 == 6", true, Boolean.class);
		evaluate("3.0f == 5.0f", false, Boolean.class);
		evaluate("3.0f == 3.0f", true, Boolean.class);
		evaluate("new java.math.BigDecimal('5') == new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') == new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('5') == new java.math.BigDecimal('3')", false, Boolean.class);
		evaluate("3 == new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('3') == 5", false, Boolean.class);
		evaluate("3L == new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("3.0d == new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("3L == new java.math.BigDecimal('3.1')", false, Boolean.class);
		evaluate("3.0d == new java.math.BigDecimal('3.1')", false, Boolean.class);
		evaluate("3.0d == new java.math.BigDecimal('3.0')", true, Boolean.class);
		evaluate("3.0f == 3.0d", true, Boolean.class);
		evaluate("10 == '10'", false, Boolean.class);
		evaluate("'abc' == 'abc'", true, Boolean.class);
		evaluate("'abc' == new java.lang.StringBuilder('abc')", true, Boolean.class);
		evaluate("'abc' == 'def'", false, Boolean.class);
		evaluate("'abc' == null", false, Boolean.class);
		evaluate("new org.springframework.expression.spel.OperatorTests$SubComparable() == new org.springframework.expression.spel.OperatorTests$OtherSubComparable()", true, Boolean.class);

		evaluate("3 eq 5", false, Boolean.class);
		evaluate("5 eQ 3", false, Boolean.class);
		evaluate("6 Eq 6", true, Boolean.class);
		evaluate("3.0f eq 5.0f", false, Boolean.class);
		evaluate("3.0f EQ 3.0f", true, Boolean.class);
		evaluate("new java.math.BigDecimal('5') eq new java.math.BigDecimal('5')", true, Boolean.class);
		evaluate("new java.math.BigDecimal('3') eq new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('5') eq new java.math.BigDecimal('3')", false, Boolean.class);
		evaluate("3 eq new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("new java.math.BigDecimal('3') eq 5", false, Boolean.class);
		evaluate("3L eq new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("3.0d eq new java.math.BigDecimal('5')", false, Boolean.class);
		evaluate("3L eq new java.math.BigDecimal('3.1')", false, Boolean.class);
		evaluate("3.0d eq new java.math.BigDecimal('3.1')", false, Boolean.class);
		evaluate("3.0d eq new java.math.BigDecimal('3.0')", true, Boolean.class);
		evaluate("3.0f eq 3.0d", true, Boolean.class);
		evaluate("10 eq '10'", false, Boolean.class);
		evaluate("'abc' eq 'abc'", true, Boolean.class);
		evaluate("'abc' eq new java.lang.StringBuilder('abc')", true, Boolean.class);
		evaluate("'abc' eq 'def'", false, Boolean.class);
		evaluate("'abc' eq null", false, Boolean.class);
		evaluate("new org.springframework.expression.spel.OperatorTests$SubComparable() eq new org.springframework.expression.spel.OperatorTests$OtherSubComparable()", true, Boolean.class);
	}
