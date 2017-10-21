	@Test
	void assertArrayEqualsByteArraysOfDifferentLengthAndMessage() {
		try {
			assertArrayEquals(new byte[] { 1, 2 }, new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <2> but was: <9>");
		}
	}
