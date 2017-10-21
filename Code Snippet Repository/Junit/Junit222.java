	@Test
	void assertArrayEqualsDeltaDoubleArraysOfDifferentLengthAndMessageSupplier() {
		try {
			assertArrayEquals(new double[] { 1.77D, 2.1, 3 }, new double[] { 8.8, 0.11 }, 1, () -> "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <3> but was: <2>");
		}
	}
