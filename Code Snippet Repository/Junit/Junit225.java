	@Test
	void assertArrayEqualsDeltaDifferentDoubleArraysAndMessageSupplier() {
		try {
			assertArrayEquals(new double[] { 0.7, 0.3001, 8 }, new double[] { 0.7, 0.4002, 8 }, 0.1, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [1], expected: <0.3001> but was: <0.4002>");
		}
	}
