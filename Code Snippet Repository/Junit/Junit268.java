	@Test
	void assertIterableEqualsIterablesOfDifferentLengthAndMessage() {
		try {
			assertIterableEquals(setOf('a', 1), setOf('a', 1, new Object()), "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "iterable lengths differ, expected: <2> but was: <3>");
		}
	}
