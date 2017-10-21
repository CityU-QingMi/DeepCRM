	@Test
	void assertArrayEqualsDifferentShortArraysAndMessage() {
		try {
			assertArrayEquals(new short[] { 1, 2, 100, -200 }, new short[] { 1, 2, 100, -500 }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [3], expected: <-200> but was: <-500>");
		}
	}
