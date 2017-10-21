	@Test
	void assertArrayEqualsDifferentDoubleArraysAndMessageSupplier() {
		try {
			assertArrayEquals(new double[] { 0.7, .1, 8 }, new double[] { 0.7, Double.MIN_VALUE, 8 }, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [1], expected: <0.1> but was: <4.9E-324>");
		}
	}
