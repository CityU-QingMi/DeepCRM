	@Test
	void assertArrayEqualsDoubleArrayVsNull() {
		try {
			assertArrayEquals(null, new double[] { Double.MAX_VALUE, 17.4, 98.7654321 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new double[] { Double.MIN_VALUE, 93.0, 92.000001 }, null);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual array was <null>");
		}
	}
