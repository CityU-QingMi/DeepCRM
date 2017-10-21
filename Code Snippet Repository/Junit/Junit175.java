	@Test
	void assertArrayEqualsLongArraysOfDifferentLengthAndMessage() {
		try {
			assertArrayEquals(new long[] { 100_000L, 200_000L, 1L, 2L }, new long[] { 1L, 2L, 3L }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <4> but was: <3>");
		}
	}
