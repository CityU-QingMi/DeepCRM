	@Test
	void assertArrayEqualsDeltaDoubleArrayVsNullAndMessage() {
		try {
			assertArrayEquals(null, new double[] { 33.3, 34.9, 20.1, 11.0011 }, 0.1, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "expected array was <null>");
		}

		try {
			assertArrayEquals(new double[] { 44.4, 20.19, 11.3, 0.11 }, null, 0.5, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "actual array was <null>");
		}
	}
