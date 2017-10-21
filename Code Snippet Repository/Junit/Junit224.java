	@Test
	void assertArrayEqualsDeltaDifferentDoubleArraysAndMessage() {
		try {
			assertArrayEquals(new double[] { 1.01, 9.031, .123, 4.23 }, new double[] { 1.1, 9.231, .13, 4.3 }, 0.1,
				"message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [1], expected: <9.031> but was: <9.231>");
		}
	}
