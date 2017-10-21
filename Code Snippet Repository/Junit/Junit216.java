	@Test
	void assertArrayEqualsDeltaDoubleArrayVsNull() {
		try {
			assertArrayEquals(null, new double[] { Double.MAX_VALUE, 11.1, 12.12 }, 0.5);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new double[] { Double.MIN_VALUE, 90, 91.9 }, null, 0.1);
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "actual array was <null>");
		}
	}
