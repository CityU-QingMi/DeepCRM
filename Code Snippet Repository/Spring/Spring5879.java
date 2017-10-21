	@Test
	public void testRelOperatorsBetween04() {
		evaluate("new java.math.BigDecimal('1') between {new java.math.BigDecimal('1'),new java.math.BigDecimal('5')}",
			"true", Boolean.class);
		evaluate("new java.math.BigDecimal('3') between {new java.math.BigDecimal('1'),new java.math.BigDecimal('5')}",
			"true", Boolean.class);
		evaluate("new java.math.BigDecimal('5') between {new java.math.BigDecimal('1'),new java.math.BigDecimal('5')}",
			"true", Boolean.class);
		evaluate("new java.math.BigDecimal('8') between {new java.math.BigDecimal('1'),new java.math.BigDecimal('5')}",
			"false", Boolean.class);
	}
