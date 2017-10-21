	@Test
	public void testMixedOperandsBigDecimal() {
		evaluate("3 * new java.math.BigDecimal('5')", new BigDecimal("15"), BigDecimal.class);
		evaluate("3L * new java.math.BigDecimal('5')", new BigDecimal("15"), BigDecimal.class);
		evaluate("3.0d * new java.math.BigDecimal('5')", new BigDecimal("15.0"), BigDecimal.class);

		evaluate("3 + new java.math.BigDecimal('5')", new BigDecimal("8"), BigDecimal.class);
		evaluate("3L + new java.math.BigDecimal('5')", new BigDecimal("8"), BigDecimal.class);
		evaluate("3.0d + new java.math.BigDecimal('5')", new BigDecimal("8.0"), BigDecimal.class);

		evaluate("3 - new java.math.BigDecimal('5')", new BigDecimal("-2"), BigDecimal.class);
		evaluate("3L - new java.math.BigDecimal('5')", new BigDecimal("-2"), BigDecimal.class);
		evaluate("3.0d - new java.math.BigDecimal('5')", new BigDecimal("-2.0"), BigDecimal.class);

		evaluate("3 / new java.math.BigDecimal('5')", new BigDecimal("1"), BigDecimal.class);
		evaluate("3 / new java.math.BigDecimal('5.0')", new BigDecimal("0.6"), BigDecimal.class);
		evaluate("3 / new java.math.BigDecimal('5.00')", new BigDecimal("0.60"), BigDecimal.class);
		evaluate("3L / new java.math.BigDecimal('5.0')", new BigDecimal("0.6"), BigDecimal.class);
		evaluate("3.0d / new java.math.BigDecimal('5.0')", new BigDecimal("0.6"), BigDecimal.class);

		evaluate("5 % new java.math.BigDecimal('3')", new BigDecimal("2"), BigDecimal.class);
		evaluate("3 % new java.math.BigDecimal('5')", new BigDecimal("3"), BigDecimal.class);
		evaluate("3L % new java.math.BigDecimal('5')", new BigDecimal("3"), BigDecimal.class);
		evaluate("3.0d % new java.math.BigDecimal('5')", new BigDecimal("3.0"), BigDecimal.class);
	}
