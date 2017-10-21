	@Test
	void assertArrayEqualsDifferentObjectArraysAndMessage() {
		try {
			assertArrayEquals(new Object[] { 1.1D, 2L, "3" }, new Object[] { 1D, 2L, "3" }, "message");
			expectAssertionFailedError();
		}
		catch (AssertionFailedError ex) {
			assertMessageStartsWith(ex, "message");
			assertMessageEndsWith(ex, "array contents differ at index [0], expected: <1.1> but was: <1.0>");
		}
	}
