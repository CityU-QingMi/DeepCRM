	@Test
	void assertArrayEqualsShortArraysOfDifferentLengthAndMessage() {
		try {
			assertArrayEquals(new short[] { 1, 2, 3, 10_000 }, new short[] { 10_000, 1, 2, 3, 4, 5, 6, 7 }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <4> but was: <8>");
		}
	}
