	@Test
	void assertArrayEqualsBooleanArraysOfDifferentLengthAndMessage() {
		try {
			assertArrayEquals(new boolean[] { true, false, false }, new boolean[] { true }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <3> but was: <1>");
		}
	}
