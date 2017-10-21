	@Test
	public void nullSafeHashCodeWithLongArray() {
		long lng = 7993l;
		int expected = 31 * 7 + (int) (lng ^ (lng >>> 32));
		lng = 84320l;
		expected = 31 * expected + (int) (lng ^ (lng >>> 32));

		long[] array = {7993l, 84320l};
		int actual = ObjectUtils.nullSafeHashCode(array);

		assertEquals(expected, actual);
	}
