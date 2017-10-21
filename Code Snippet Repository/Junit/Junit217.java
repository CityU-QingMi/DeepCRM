	@Test
	void assertArrayEqualsDifferentBooleanArraysAndMessage() {
		try {
			assertArrayEquals(new boolean[] { true, true }, new boolean[] { false, true }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [0], expected: <true> but was: <false>");
		}
	}
