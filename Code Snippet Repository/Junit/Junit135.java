	@Test
	void assertArrayEqualsCharArraysOfDifferentLengthAndMessage() {
		try {
			assertArrayEquals(new char[] { 'a', 's', 'd' }, new char[] { 'd' }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <3> but was: <1>");
		}
	}
