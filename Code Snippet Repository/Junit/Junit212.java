	@Test
	void assertArrayEqualsDifferentDoubleArraysAndMessage() {
		try {
			assertArrayEquals(new double[] { 1.01, 9.031, .123, 4.23 }, new double[] { 1.01, 9.099, .123, 4.23 },
				"message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [1], expected: <9.031> but was: <9.099>");
		}
	}
