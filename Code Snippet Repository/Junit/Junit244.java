	@Test
	void assertEqualsDouble() {
		assertEquals(1.0d, 1.0d);
		assertEquals(Double.NaN, Double.NaN);
		assertEquals(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
		assertEquals(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
		assertEquals(Double.MIN_VALUE, Double.MIN_VALUE);
		assertEquals(Double.MAX_VALUE, Double.MAX_VALUE);
		assertEquals(Double.MIN_NORMAL, Double.MIN_NORMAL);
	}
