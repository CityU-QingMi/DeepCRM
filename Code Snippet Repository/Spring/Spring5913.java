	@Test
	public void testBigIntegers() {
		evaluate("3 + new java.math.BigInteger('5')", new BigInteger("8"), BigInteger.class);
		evaluate("3 - new java.math.BigInteger('5')", new BigInteger("-2"), BigInteger.class);
		evaluate("3 * new java.math.BigInteger('5')", new BigInteger("15"), BigInteger.class);
		evaluate("3 / new java.math.BigInteger('5')", new BigInteger("0"), BigInteger.class);
		evaluate("5 % new java.math.BigInteger('3')", new BigInteger("2"), BigInteger.class);
		evaluate("new java.math.BigInteger('5') % 3", new BigInteger("2"), BigInteger.class);
		evaluate("new java.math.BigInteger('5') ^ 3", new BigInteger("125"), BigInteger.class);
	}
