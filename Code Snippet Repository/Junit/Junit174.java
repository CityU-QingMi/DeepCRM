	@Test
	void assertArrayEqualsLongArraysOfDifferentLength() {
		try {
			assertArrayEquals(new long[] { 1, 2, 3, Long.MIN_VALUE, 4 }, new long[] { 1, Long.MAX_VALUE, 2 });
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageEquals(ex, "array lengths differ, expected: <5> but was: <3>");
		}
	}
