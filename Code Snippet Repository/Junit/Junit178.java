	@Test
	void assertArrayEqualsDifferentLongArraysAndMessage() {
		try {
			assertArrayEquals(new long[] { 6, 5, 4, 3, 2, Long.MIN_VALUE }, new long[] { 6, 5, 4, 3, 2, 1 }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex,
				"array contents differ at index [5], expected: <-9223372036854775808> but was: <1>");
		}
	}
