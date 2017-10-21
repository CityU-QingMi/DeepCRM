	@Test
	public void testBigDecimals() {
		evaluate("3 + new java.math.BigDecimal('5')", new BigDecimal("8"), BigDecimal.class);
		evaluate("3 - new java.math.BigDecimal('5')", new BigDecimal("-2"), BigDecimal.class);
		evaluate("3 * new java.math.BigDecimal('5')", new BigDecimal("15"), BigDecimal.class);
		evaluate("3 / new java.math.BigDecimal('5')", new BigDecimal("1"), BigDecimal.class);
		evaluate("5 % new java.math.BigDecimal('3')", new BigDecimal("2"), BigDecimal.class);
		evaluate("new java.math.BigDecimal('5') % 3", new BigDecimal("2"), BigDecimal.class);
		evaluate("new java.math.BigDecimal('5') ^ 3", new BigDecimal("125"), BigDecimal.class);
	}
