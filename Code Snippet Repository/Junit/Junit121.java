	@Test
	void assertArrayEqualsObjectArraysOfDifferentLengthAndMessage() {
		try {
			assertArrayEquals(new Object[] { 'a', 1 }, new Object[] { 'a', 1, new Object() }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array lengths differ, expected: <2> but was: <3>");
		}
	}
