	@Test
	void assertArrayEqualsDeltaDifferentDoubleArrays() {
		try {
			assertArrayEquals(new double[] { 1.12, 2.92, 1.201 }, new double[] { 1.1201, 2.94, 1.201 }, 0.01);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [1], expected: <2.92> but was: <2.94>");
		}

		try {
			assertArrayEquals(new double[] { 0.6, 0.12, 19.9, 5.5 }, new double[] { 1.0, 0.42, 20, Double.NaN }, 0.5);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array contents differ at index [3], expected: <5.5> but was: <NaN>");
		}
	}
