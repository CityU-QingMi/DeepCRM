	@Test
	void assertArrayEqualsDifferentDoubleArrays() {
		try {
			assertArrayEquals(new double[] { 1.17, 1.19, 1.21, 5 }, new double[] { 1.17, 1.00019, 1.21, 5 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [1], expected: <1.19> but was: <1.00019>");
		}

		try {
			assertArrayEquals(new double[] { 0.1, 0.2, 0.3, 0.4, 0.5 },
				new double[] { 0.1, 0.2, 0.3, 0.4, Double.NaN });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [4], expected: <0.5> but was: <NaN>");
		}
	}
