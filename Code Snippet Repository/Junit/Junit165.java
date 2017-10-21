	@Test
	void assertArrayEqualsIntArraysOfDifferentLengthAndMessage() {
		try {
			assertArrayEquals(new int[] { 100_000, 200_000, 1, 2 }, new int[] { 1, 2, 3 }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <4> but was: <3>");
		}
	}
