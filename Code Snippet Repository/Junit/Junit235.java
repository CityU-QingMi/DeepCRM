	@Test
	void assertEqualsFloat() {
		assertEquals(1.0f, 1.0f);
		assertEquals(Float.NaN, Float.NaN);
		assertEquals(Float.NEGATIVE_INFINITY, Float.NEGATIVE_INFINITY);
		assertEquals(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
		assertEquals(Float.MIN_VALUE, Float.MIN_VALUE);
		assertEquals(Float.MAX_VALUE, Float.MAX_VALUE);
		assertEquals(Float.MIN_NORMAL, Float.MIN_NORMAL);
		assertEquals(Double.NaN, Float.NaN);
	}
